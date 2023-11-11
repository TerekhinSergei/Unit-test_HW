package third.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest {
    private int lastIdx = 0;

    User createUser(boolean admin, boolean authenticated) {
        int idx = ++lastIdx;

        User result = new User("user" + idx, "password" + idx, admin);
        result.isAuthenticate = authenticated;

        return result;
    }

    UserRepository createRepository(User... users) {
        UserRepository result = new UserRepository();

        for (User user : users) {
            result.addUser(user);
        }

        return result;
    }

    @Test
    void batchLogoffDoesNotLogoffAdmins() {
        User ann = createUser(true, true);
        User bob = createUser(true, false);
        User cam = createUser(true, true);

        var testee = createRepository(ann, bob, cam);
        testee.batchLogoff();

        assertTrue(ann.isAuthenticate);
        assertFalse(bob.isAuthenticate);
        assertTrue(cam.isAuthenticate);
    }

    @Test
    void batchLogoffLogsOffNotAdmins() {
        User ann = createUser(false, true);
        User bob = createUser(false, false);
        User cam = createUser(false, true);

        var testee = createRepository(ann, bob, cam);
        testee.batchLogoff();

        assertFalse(ann.isAuthenticate);
        assertFalse(bob.isAuthenticate);
        assertFalse(cam.isAuthenticate);
    }
}