package edu.planner.enums;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void whenToEnumParamIsNull() {
        assertNull(Profile.toEnum(null), "Should return null value");
    }

    @Test
    void whenToEnumParamIsValid() {
        assertEquals(Profile.TEACHER, Profile.toEnum((short) 3), "Should return enum");
    }

    @Test
    void whenGetDescription() {
        assertEquals("ROLE_ADMIN", Profile.ADMIN.getDescription(), "Should return valid description");
    }

    @Test
    void whenProfilesToShortsParamIsNull() {
        assertTrue(Profile.profilesToShorts(null).isEmpty(), "Should return empty short list");
    }

    @Test
    void whenProfilesToShortsParamIsEmpty() {
        assertTrue(Profile.profilesToShorts(new HashSet<>()).isEmpty(), "Should return empty short list");
    }

    @Test
    void whenProfilesToShortsParamIsValid() {
        Set<Short> actualProfiles = Profile.profilesToShorts(Set.of(Profile.ADMIN, Profile.TEACHER));
        Set<Short> expectedProfiles = Set.of(Profile.ADMIN.getId(), Profile.TEACHER.getId());
        assertEquals(expectedProfiles, actualProfiles, "Should return expected Profile Ids");
    }

    @Test
    void whenShortsToProfilesParamIsNull() {
        assertTrue(Profile.shortsToProfiles(null).isEmpty(), "Should return empty short list");
    }

    @Test
    void whenShortsToProfilesParamIsEmpty() {
        assertTrue(Profile.shortsToProfiles(new HashSet<>()).isEmpty(), "Should return empty short list");
    }

    @Test
    void whenShortsToProfilesParamIsValid() {
        Set<Profile> actualProfiles = Profile.shortsToProfiles(Set.of(Profile.COORDINATOR.getId(), Profile.TEACHER.getId()));
        Set<Profile> expectedProfiles = Set.of(Profile.COORDINATOR, Profile.TEACHER);
        assertEquals(expectedProfiles, actualProfiles, "Should return expected Profile");
    }
}