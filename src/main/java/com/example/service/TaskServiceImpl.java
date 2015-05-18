package com.example.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.FromConfig;
import com.example.actor.TaskActor;
import com.example.domain.Task;
import com.example.repository.TaskRepository;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by soumya on 5/18/2015.
 */
@Service
public class TaskServiceImpl implements ITaskService {

    TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public void distributeTasks() {
        final ActorSystem system = ActorSystem.create("TaskSystem",
                ConfigFactory.load().getConfig("TaskSystem"));

        ActorRef taskRouter = system.actorOf(
                TaskActor.props(taskRepository).withRouter(new FromConfig()),
                "TaskRouter");

        for (int i = 1; i < 12; i++) {
            Task task = new Task();
            task.setName("Task " + i);
            task.setDescription("Description of task "+i);
            taskRouter.tell(task, null);
        }
    }
}
