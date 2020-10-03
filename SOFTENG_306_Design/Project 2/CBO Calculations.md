# CBO Calculations

## MainComponent

- SubComponent
- Course
- CourseMgr*
- CourseRegistrationMgr*
- FILEMgr*
- Mark
- MarkMgr*

Total: 7

## Mark

- Student
- Course
- CourseworkComponent
- MainComponent
- SubComponent
- FILEMgr*
- Main*
- MarkMgr*

8 in total

## MarkMgr*

- CourseworkComponent
- MainComponent
- SubComponent
- Mark
- FILEMgr
- ValidationMgr
- Course
- Student
- Main
- CourseRegistrationMgr

Total: 8

## Professor

- ValidationMgr*
- Course
- CourseMgr*
- CourseRegistrationMgr*
- FILEMgr*
- Main* 
- ProfessorMgr*
- HelpInfoMgr*

Total: 8

## ProfessorMgr*

- ValidationMgr
- Professor
- HelpInfoMgr

Total: 3

??? ProfessorMgr isnt used by anything???

## Student

- Mark
- CourseRegistration
- CourseRegistrationMgr*
- FILEMgr*
- HelpInfoMgr*
- Main*
- MarkMgr*
- StudentMgr*
- ValidationMgr*

Total: 9

## StudentMgr

- 

