package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

/**
 * @author Alessandro Arosio - 03/11/2019 17:17
 */
@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock(lenient = true)
  private SpecialtyRepository specialtyRepository;

  @InjectMocks
  SpecialitySDJpaService service;

  @Test
  void findById() {
    Speciality speciality = new Speciality();

    when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

    Speciality specialityFound = service.findById(1L);

    assertThat(specialityFound).isNotNull();

    verify(specialtyRepository).findById(1L);
  }

  @Test
  void findByIdBddTest() {

    // given
    Speciality speciality = new Speciality();

    given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

    // when
    Speciality specialityFound = service.findById(1L);


    // then
    assertThat(specialityFound).isNotNull();
    then(specialtyRepository).should(times(1)).findById(anyLong());
    then(specialtyRepository).shouldHaveNoMoreInteractions();
//    then(specialtyRepository).should().findById(anyLong());
  }

  @Test
  void deleteById() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, atLeastOnce()).deleteById(1L);
  }

  @Test
  void deleteByIdAtMost() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, atMost(5)).deleteById(1L);
  }

  @Test
  void deleteByIdAtLeast() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, times(2)).deleteById(1L);
  }

  @Test
  void testDeleteByObject() {
    Speciality speciality = new Speciality();

    service.delete(speciality);

    verify(specialtyRepository).delete(any(Speciality.class));
  }

  @Test
  void deleteByIdNever() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, never()).deleteById(5L);
  }

  @Test
  void testDelete() {
    service.delete(new Speciality());
  }


  @Test
  void testDoThrow() {
    doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

    assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

    verify(specialtyRepository).delete(any());
  }

  @Test
  void testFindByIdThrows() {

    given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

    assertThrows(RuntimeException.class, () -> specialtyRepository.findById(1L));

    then(specialtyRepository).should().findById(1L);
  }

  @Test
  void testDeleteBDD() {
    willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

    assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

    then(specialtyRepository).should().delete(any());
  }

  @Test
  void testSaveLambda() {
    // given
    final String MATCH_ME = "MATCH_ME";
    Speciality speciality = new Speciality();
    speciality.setDescription(MATCH_ME);

    Speciality savedSpeciality = new Speciality();
    savedSpeciality.setId(1L);

    // need mock to only return on match MATCH_ME string
    given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

    // when
    Speciality returnedSpecialty = service.save(speciality);

    // then
    assertThat(returnedSpecialty.getId()).isEqualTo(1L);
  }

  @Test
  void testSaveLambdaNoMatch() {
    // given
    final String MATCH_ME = "MATCH_ME";
    Speciality speciality = new Speciality();
    speciality.setDescription("not a match");

    Speciality savedSpeciality = new Speciality();
    savedSpeciality.setId(1L);

    // need mock to only return on match MATCH_ME string
    given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

    // when
    Speciality returnedSpecialty = service.save(speciality);

    // then
//    assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    assertNull(returnedSpecialty);
  }
}