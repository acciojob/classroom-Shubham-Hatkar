package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository
{
    HashMap<String, Student> studentDB = new HashMap<>(); // studentName : studentObj

    HashMap<String, Teacher> teacherDB = new HashMap<>(); // teacherName : teacherObj

    HashMap<String, List<String>> teacherAndItsDB = new HashMap<>(); // teacherName:list of student_name


    public void addStudent(Student student)
    {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher)
    {
        teacherDB.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
        // Adding existing pair so check if it is available or not
        if(studentDB.containsKey(student) && teacherDB.containsKey(teacher))
        {
            List<String> studentList = new ArrayList<>();
            if(teacherAndItsDB.containsKey(teacher)) // if the list already exist
                studentList = teacherAndItsDB.get(teacher);

            studentList.add(student); // add student in list
            teacherAndItsDB.put(teacher,studentList); // add list in DB
        }
    }

    public Student getStudentByName(String name)
    {
        if(studentDB.containsKey(name))
            return studentDB.get(name);
        else return null;
    }


    public Teacher getTeacherByName(String name)
    {
        if(teacherDB.containsKey(name)) return teacherDB.get(name);
        return null;
    }


    public List<String> getStudentsByTeacherName(String teacher)
    {
        if(teacherAndItsDB.containsKey(teacher)) return teacherAndItsDB.get(teacher);
        return null;
    }


    public List<String> getAllStudents()
    {
        List<String> list = new ArrayList<>();
        for(String name : studentDB.keySet())
        {
            list.add(name);
        }
        return list;
    }

    public void deleteTeacherByName(String teacher)
    {
        if(teacherAndItsDB.containsKey(teacher)) teacherAndItsDB.remove(teacher);
        if(teacherDB.containsKey(teacher)) teacherDB.remove(teacher);
    }

    public void deleteAllTeachers()
    {
        teacherDB.clear();
        studentDB.clear();
        teacherAndItsDB.clear();
    }
}
