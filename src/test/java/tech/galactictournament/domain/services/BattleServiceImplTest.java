package tech.galactictournament.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.galactictournament.domain.dtos.BattleRequestDTO;
import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.entities.Battle;
import tech.galactictournament.domain.entities.Specie;
import tech.galactictournament.domain.mappers.BattleMapper;
import tech.galactictournament.domain.repositories.BattleRepository;
import tech.galactictournament.domain.repositories.BattleStatisticsRepository;
import tech.galactictournament.domain.repositories.SpecieRepository;

    @ExtendWith(MockitoExtension.class)
    class BattleServiceImplTest {

        @Mock private SpecieRepository specieRepository;
        @Mock private BattleRepository battleRepository;
        @Mock private BattleStatisticsRepository statsRepository;
        @Mock private BattleMapper battleMapper;

        @InjectMocks
        private tech.galactictournament.domain.services.BattleServiceImpl battleService;

        private Specie specieLeft;
        private Specie specieRight;

        @BeforeEach
        void setUp() {
            specieLeft = new Specie();
            specieLeft.setId(1L);
            specieLeft.setName("Dragon");
            specieLeft.setPowerLevel(80);
            specieLeft.setSpecialAbility("Volar");
            specieLeft.setCreatedAt(LocalDateTime.now());

            specieRight = new Specie();
            specieRight.setId(2L);
            specieRight.setName("Gigante");
            specieRight.setPowerLevel(70);
            specieRight.setSpecialAbility("Super Fuerza");
            specieRight.setCreatedAt(LocalDateTime.now());
        }

        private BattleRequestDTO request(Long leftId, Long rightId) {
            BattleRequestDTO req = new BattleRequestDTO();
            req.setFighterLeftId(leftId);
            req.setFighterRightId(rightId);
            return req;
        }

        private Battle stubbedSave(Specie winner) {
            Battle saved = new Battle();
            saved.setId(10L);
            saved.setFighterLeft(specieLeft);
            saved.setFighterRight(specieRight);
            saved.setWinner(winner);
            saved.setBattleDate(LocalDateTime.now());
            when(battleRepository.save(any())).thenReturn(saved);
            when(statsRepository.findBySpecies(any())).thenReturn(Optional.empty());
            when(statsRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
            BattleDTO dto = new BattleDTO();
            SpecieDTO winnerDto = new SpecieDTO();
            winnerDto.setId(winner.getId());
            winnerDto.setName(winner.getName());
            winnerDto.setPowerLevel(winner.getPowerLevel());
            dto.setId(10L);
            dto.setWinner(winnerDto);
            when(battleMapper.toDTO(saved)).thenReturn(dto);
            return saved;
        }

        @Test
        void startBattle_mismaEspecie_lanzaIllegalArgument() {
            assertThatThrownBy(() -> battleService.startBattle(request(1L, 1L)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("no puede combatir contra sí misma");
        }

        @Test
        void startBattle_faltaUnPeleador_lanzaNoSuchElement() {
            when(specieRepository.findById(1L)).thenReturn(Optional.of(specieLeft));
            when(specieRepository.findById(2L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> battleService.startBattle(request(1L, 2L)))
                    .isInstanceOf(NoSuchElementException.class);
        }

        @Test
        void startBattle_probarGanador_porPoder() {
            when(specieRepository.findById(1L)).thenReturn(Optional.of(specieLeft));
            when(specieRepository.findById(2L)).thenReturn(Optional.of(specieRight));
            stubbedSave(specieLeft);

            battleService.startBattle(request(1L, 2L));

            ArgumentCaptor<Battle> captor = ArgumentCaptor.forClass(Battle.class);
            verify(battleRepository).save(captor.capture());
            assertThat(captor.getValue().getWinner()).isEqualTo(specieLeft);
        }

        @Test
        void startBattle_mismoNivel_empate_porNombre() {
            specieLeft.setPowerLevel(60);
            specieRight.setPowerLevel(60);
            specieLeft.setName("Aaa");
            specieRight.setName("Bbb");

            when(specieRepository.findById(1L)).thenReturn(Optional.of(specieLeft));
            when(specieRepository.findById(2L)).thenReturn(Optional.of(specieRight));
            stubbedSave(specieLeft);

            battleService.startBattle(request(1L, 2L));

            ArgumentCaptor<Battle> captor = ArgumentCaptor.forClass(Battle.class);
            verify(battleRepository).save(captor.capture());
            assertThat(captor.getValue().getWinner()).isEqualTo(specieLeft);
        }
    }
