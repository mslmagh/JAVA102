import org.junit.Test;
import static org.junit.Assert.*;

public class MyTest {
        @Test
       public void testAddCharacter() throws PartyLimitReachedException, AlreadyInPartyException {
            Party party = new Party();
            PlayableCharacter character = new Paladin("vff"); // Paladin tipinde bir karakter oluşturuldu
            party.addCharacter(character);
            assertTrue(party.getFighters().contains(character)); // Karakterin savaşçılar listesine eklendiği kontrol ediliyor
        }
    
        @Test
        public void testRemoveCharacter() throws PartyLimitReachedException, AlreadyInPartyException, CharacterIsNotInPartyException {
            Party party = new Party();
            PlayableCharacter character = new Paladin("vff"); // Paladin tipinde bir karakter oluşturuldu
            party.addCharacter(character);
            party.removeCharacter(character);
            assertFalse(party.getFighters().contains(character)); // Karakterin savaşçılar listesinden çıkarıldığı kontrol ediliyor
        }
    
        @Test
        public  void testPartyLevelUp() throws PartyLimitReachedException, AlreadyInPartyException {
            Party party = new Party();
            PlayableCharacter character = new Paladin("vff"); // Paladin tipinde bir karakter oluşturuldu
            party.addCharacter(character);
            int initialLevel = character.getLevel();
            party.partyLevelUp();
            assertEquals(initialLevel + 1, character.getLevel()); // Karakterin seviyesinin arttığı kontrol ediliyor
        }
    
        @Test
        public void testGetMixedCount() throws PartyLimitReachedException, AlreadyInPartyException {
            Party party = new Party();
            PlayableCharacter character = new Paladin("vff"); // Paladin tipinde bir karakter oluşturuldu
            party.addCharacter(character);
            assertEquals(1, party.getMixedCount()); // Karakterin eklenmesiyle mixedCount'un arttığı kontrol ediliyor
        }
    }
