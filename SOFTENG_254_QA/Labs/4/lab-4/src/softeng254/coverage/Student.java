package softeng254.coverage;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Version distributed for SOFTENG 254 test coverage lab.
 */
public class Student {
    /**
     * Represents the first name of the student.
     * 
     * The implementation ensures that this field is never null once
     * the object is constructed.
     */
    private String _firstName;
    
    /**
     * Represents the last name of a student.
     * 
     * The implementation ensures that this field is never null once
     * the object is constructed.
     */
    private String _lastName;
    
    /**
     * Represents the preferred name of a student.
     * 
     * The implementation ensures that this field is never null once
     * the object is constructed.
     */
    private String _preferredName;
    
    /**
     * The (possibly empty) list of middle names, in correct order, of the student
     * 
     * The implementation ensures that this field is never null once
     * the object is constructed.
     */
    private List<String> _middleNames;
    
    /**
     * Create a Student object with the specified real name. Names are considered as "first"
     * (or given) name, "middle" name(s), and "last" (or family) name. Students may have 
     * more than one, or no, middle names. Students who only have one name (e.g., "Pele"), 
     * this name will be regarded as their last name.
     * 
     * @param first The first name of the student. May be the empty string (but only
     *         if middle is also empty), indicating the student has only a last name.
     * @param middle The middle name of the student (when the student only has one
     *                middle name). May be the empty string, indicating the student
     *                has no middle name.
     * @param last The last name of the student. Must not be an empty string.
     * @throws RuntimeException if there is not last name. (Note that none of the
     *          tests actually test this behaviour.)
     */
    public Student(String first, String middle, String last) {
        this (first, constructMiddleAsList(middle), last);
    }
    
    /**
     * Create a Student object with the specified real name. Names are considered as "first"
     * (or given) name, "middle" name(s), and "last" (or family) name. Students may have 
     * more than one, or no, middle names. Students who only have one name (e.g., "Pele"), 
     * this name will be regarded as their last name.
     * 
     * @param first The first name of the student. May be the empty string (but only
     *         if middle is also empty), indicating the student has only a last name.
     * @param middle The list of middle names of the student. The list may be empty,
     *                indicating the student has no middle names.
     * @param last The last name of the student. Must not be an empty string.
     * @throws RuntimeException if there is not last name. (Note that none of the
     *          tests actually test this behaviour.)
     */
    public Student(String first, List<String> middleNames, String last) {
        _firstName = first;
        if (_firstName == null) {
            _firstName = "";
        }
        _middleNames = middleNames;
        if (_middleNames == null) {
            _middleNames = new Vector<String>();
        }
        _lastName = last;
        if (_lastName == null || _lastName == "") {
            throw new RuntimeException("Missing last name");
        }
        _preferredName = "";
    }
    
    /**
     * Set the preferred name of the student to what is specified, when the student uses
     * a name that is different from their real name. The complete name must be used. 
     * For example, if "Ronald Belford Scott" is usually known as "Bon Scott", then the
     * preferred name should be set to "Bon Scott", not just "Bon" because the last name
     * is unchanged.
     * @param preferred
     */
    public void setPreferredName(String preferred) {
        _preferredName = preferred;
        if (_preferredName == null) {
            _preferredName = "";
        }
    }
    /**
     * Provide a string version of the student's full name.
     * @return A string consisting of the first name, middle names, and last name, 
     *          each separated by one space.
     */
    public String fullName() {
        String first = _firstName;
        if (first != "") {
            first += " ";
        }
        String middle = "";
        for (String m: _middleNames) {
            middle += m + " ";
        }
        return first + middle + _lastName;
    }
    /**
     * Provide a string version of the student's preferred name.
     * @return If a preferred name has been set (@see setPreferredName),
     *          the return that, otherwise return the first name and last name
     *          separated by one space.
     */
    public String preferredName() {
        if (_preferredName != "") {
            return _preferredName;
        } else {
            if (_firstName != "") {
                return _firstName + " " + _lastName;
            } else {
                return _lastName;
            }
        }
    }
    /**
     * Provide a string version of the student's name, where the last name is first
     * @return A string consisting of the last name, followed by ",", followed by the 
     *          first name, middle names, and last name, each separated by one space.
     */
    public String lastNameFirst() {
        String first = _firstName;
        if (first != "") {
            first = ", " + first;
        }
        String middle = "";
        for (String m: _middleNames) {
            middle += " " + m;
        }
        return _lastName + first + middle;
    }
    
    /**
     * Provide a string version of the student's full name.
     * @return A string consisting of the first name, middle names, and last name, 
     *          each separated by one space.
     */
    public String initialsName() {
        String f = "";
        if (_firstName != "") {
            f = ""+ _firstName.charAt(0);
            if (_firstName.length() > 1) {
                f += ". ";
            } else {
                f += " ";
            }
        }
        String middle = "";
        for (String m: _middleNames) {
            if (m.length() > 1) {
                middle += m.charAt(0) + ". ";
            } else {
                middle += m.charAt(0) + " ";
            }
        }
        
        return f + middle + _lastName;
    }
    
    /**
     * Provide a "upi" version of the students' name.
     * @return A string consisting of the first letter of the students' first name, and the
     *          first three letters of the last name.
     */
    public String upi() {
        String fi = "";
        if (_firstName != "") {
            fi += _firstName.charAt(0);
        }
        fi = fi.toLowerCase();
        int last = 3;
        if (_lastName.length() < 3) {
            last = _lastName.length();
        }
        String lastthree = _lastName.substring(0,last).toLowerCase();
        return fi + lastthree;
    }
    
    /**
     * Create a string version of this student.
     * @return A string corresponding to what the method fullName() returns.
     */
    public String toString() {
        return fullName();
    }
    
    /**
     * Determine whether this student is the same as the object provided by name. 
     * @return true if and only if the provided object is a student object, and all 
     *          of the parts of the name (first, middle, last, and preferred) are equal.
     */
    public boolean equals(Object obj) {
        if (obj== null) {
            return false;
        }
        Student other = (Student)obj;
        if (!_firstName.equals(other._firstName)) {
            return false;
        }
        if (!_preferredName.equals(other._preferredName)) {
            return false;
        }
        if (!_lastName.equals(other._lastName)) {
            return false;
        }
        if (_middleNames.size() != other._middleNames.size()) {
            return false;
        }
        Iterator<String> myIter = _middleNames.iterator();
        Iterator<String> otherIter = other._middleNames.iterator();
        // Now we know both lists are the same size so we only
        // need to test termination of one list
        while (myIter.hasNext()) {
            // Note that the use of these local variables is not,
            // strictly-speaking, necessary.
            String myMiddle = (String)myIter.next();
            String otherMiddle = (String)otherIter.next();
            if (!myMiddle.equals(otherMiddle)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Take a single middle name as a string and return it as a list.
     * 
     * The rules for the use of "this" as an explicit constructor call 
     * requires that it be the first statement in the constructor. This
     * means we can't do any pre-processing of arguments before the call,
     * which is a bit of a nuisance. However we can get around this by
     * having a helper method that does the pre-processing for us, and then
     * calling it during the "this" call.
     * 
     * The rules for invoking local methods in an explicit constructor call
     * require that the method be static.
     * 
     * @param middle The middle name
     * @return A list containing the above middle name, but only if it's non empty
     *          (in which case return an empty list)
     */
    private static List<String> constructMiddleAsList(String middle) {
        List<String> middles = new Vector<String>();
        if (middle != null && middle != "") {
            middles.add(middle);
        }
        return middles;
    }
}
