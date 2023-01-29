package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class TaskMap {
    public Map<String, List<Task>> value;
    public TaskMap(Map<String, List<Task>> value){
        this.value=value;}

    public Map<String, List<Task>> getTaskMap(){
        return value;
        }

    public Set<Entry<String, List<Task>>> entrySet(){
        return value.entrySet();
        }
    public  List<Task> put(String name, ArrayList<Task> list){
        return value.put(name, list);
        }

    public List<Task> get(String project){
        return value.get(project);
        }
    }
