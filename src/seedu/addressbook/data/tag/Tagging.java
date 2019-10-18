package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

public class Tagging {
    Tag tag; 
    Person person;
    String operation;

    Tagging (Tag tag, Person person, String operation) {
        this.tag = tag;
        this.person = person;
        this.operation = operation;
    }
}
