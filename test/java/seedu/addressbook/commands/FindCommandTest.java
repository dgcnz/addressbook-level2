package java.seedu.addressbook.commands;

import org.junit.jupiter.api.Test;
import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.FindCommand;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindCommandBehavior(new String[]{"Amy"}, Arrays.asList(td.getAmy()));

        //same word, different case: not matched
        assertFindCommandBehavior(new String[]{"aMy"}, Collections.emptyList());

        //partial word: not matched
        assertFindCommandBehavior(new String[]{"my"}, Collections.emptyList());

        //multiple words: matched
        assertFindCommandBehavior(new String[]{"Amy", "Bill", "Candy", "Destiny"},
                Arrays.asList(td.getAmy(), td.getBill(), td.getCandy()));

        //repeated keywords: matched
        assertFindCommandBehavior(new String[]{"Amy", "Amy"}, Arrays.asList(td.getAmy()));

        //Keyword matching a word in address: not matched
        assertFindCommandBehavior(new String[]{"Clementi"}, Collections.emptyList());
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindCommand command = createFindCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    /**
     * Factory method that creates and returns a {@code FindCommand}.
     * @param keywords list of keywords to search for.
     * @return an instance of {@FindCommand} that searches the test AddressBook.
     */
    private FindCommand createFindCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand command = new FindCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
