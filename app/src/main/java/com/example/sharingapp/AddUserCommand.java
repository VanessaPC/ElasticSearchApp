package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Created by rosacristobalcastro on 17/2/18.
 */

public class AddUserCommand extends Command{
    private User user;

    public AddUserCommand(User user) {
        this.user = user;
    }

    // Save the item remotely to server
    public void execute(){
        ElasticSearchManager.AddUserTask add_user_task = new ElasticSearchManager.AddUserTask();
        add_user_task.execute(user);

        // use get() to access the return of AddUserTask. i.e. AddUserTask returns a Boolean to
        // indicate if the user was successfully saved to the remote server
        try {
            if(add_user_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
