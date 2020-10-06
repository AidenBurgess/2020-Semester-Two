# CBO Calculations

## Course - seems good

- FILEMgr
- CourseMgr
- CourseRegistrationMgr
- HelpInfoMgr
- MarkMgr
- ValidationMgr

Total: 6

## CourseMgr - seems good

- Course
- Main
- CourseworkComponent
- FILEMgr
- HelpInfoMgr
- LabGroup
- LectureGroup
- MainComponent
- Professor
- SubComponent
- TutorialGroup
- ValidationMgr
- Group

Total: 13

## CourseRegistration - seems good

- CourseRegistrationMgr
- FILEMgr
- ValidationMgr

Total: 3

## CourseRegistrationMgr - Professor missing? Have added

- Course.
- CourseRegistration
- Main.
- FILEMgr.
- HelpInfoMgr.
- MarkMgr.
- Professor
- Student.
- ValidationMgr.
- - Group

Total: 9

## CourseworkComponent - seems good

- CourseMgr.
- FILEMgr.
- Mark.
- MarkMgr.

Total: 4

## FILEMgr - seems good

- Course.
- CourseRegistration.
- CourseworkComponent.
- StudentMgr.
- Main.
- CourseMgr.
- CourseRegistrationMgr.
- MarkMgr.
- Group.
- LabGroup.
- LectureGroup.
- MainComponent.
- Mark.
- Professor.
- Student.
- SubComponent.
- TutorialGroup.

Total: 17

## Group - seems good (CourseRegistrationMgr has list of Group)

- CourseMgr
- FILEMgr
- HelpInfoMgr

Total: 3

## HelpInfoMgr (Proffessor and Student missing, have added)

- Course.
- Group.
- CourseMgr.
- ValidationMgr
- StudentMgr.
- ProfessorMgr.
- CourseRegistrationMgr.
- Main.
- Professor
- Student

Total: 10

## LabGroup - seems good

Parent:

- CourseMgr
- FILEMgr
- HelpInfoMgr

Total: 3

## LectureGroup - seems good

- Parent:

  - CourseMgr
  - FILEMgr
  - HelpInfoMgr

  Total: 3

## Main (Need to look at ValidationMgr and Student)

- CourseMgr.
- CourseRegistrationMgr.
- FILEMgr.
- HelpInfoMgr.
- Student
- StudentMgr.
- ValidationMgr
- MarkMgr.

Total: 8

## MainComponent (Seems good)

Parent:

- CourseMgr
- FILEMgr
- Mark
- MarkMgr

Total: 4

## Mark

- CourseworkComponent.
- FILEMgr.
- MarkMgr.

Total: 3

## MarkMgr - seems good

- Course.
- CourseworkComponent.
- FILEMgr.
- Main.
- Mark.
- CourseRegistrationMgr.
- Student.
- ValidationMgr.

Total: 9

## Professor - seems good

- FILEMgr. 
- ProfessorMgr.
- HelpInfoMgr.
- ValidationMgr.
- CourseMgr.
- CourseRegistrationMgr.

Total: 6

## ProfessorMgr - seems good

- HelpInfoMgr.
- Professor.
- ValidationMgr.

Total: 3

??? ProfessorMgr isnt used by anything???

## Student - seems good

- Main.
- StudentMgr.
- FILEMgr.
- CourseRegistrationMgr.
- HelpInfoMgr.
- MarkMgr.
- ValidationMgr

Total: 7

## StudentMgr - seems good

- FILEMgr.
- HelpInfoMgr.
- Main.
- Student.
- ValidationMgr.

Total: 5

## SubComponent - seems good

Parent:

- CourseMgr
- FILEMgr
- Mark
- MarkMgr

Total: 4

## TutorialGroup - seems good

- Parent:

  - CourseMgr
  - FILEMgr
  - HelpInfoMgr

  Total: 3

## ValidationMgr - seems good

- Course.
- CourseRegistration.
- HelpInfoMgr.
- Main.
- Professor.
- Student.
- CourseMgr.
- ProfessorMgr
- StudentMgr.
- CourseRegistrationMgr.
- MarkMgr.

Total: 11

