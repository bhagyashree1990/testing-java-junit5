package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Owner Map Service Test : ")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService=new PetTypeMapService();
        petService=new PetMapService();
        ownerMapService=new OwnerMapService(petTypeService,petService);
        System.out.println("First Before Each");
    }

    @DisplayName("Verify zero owners")
    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

    @Nested
    @DisplayName("Pet type : ")
    class TestCreatePetTypes{
        @BeforeEach
        void setUp() {
            PetType type1=new PetType(1L,"Dog");
            PetType type2=new PetType(2L,"Cat");
            petTypeService.save(type1);
            petTypeService.save(type2);
            System.out.println("Nested Before Each");
        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @Nested
        @DisplayName("Save Owner Tests : ")
        class SaveOwnerTests{

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L,"John","Doe"));
                System.out.println("Nested - Save Owner - Before Each");
            }

            @Test
            void saveOwner() {
                Owner owner=new Owner(2L,"Jane","Doe");
                Owner savedOwner = ownerMapService.save(owner);

                assertThat(savedOwner).isNotNull();
            }

            @Nested
            @DisplayName("Find Owner Tests : ")
            class FindOwnerTests{

                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {
                    Owner foundOwner = ownerMapService.findById(2L);
                    assertThat(foundOwner).isNull();
                }
            }
        }

    }

    @DisplayName("Verify still zero owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }
}