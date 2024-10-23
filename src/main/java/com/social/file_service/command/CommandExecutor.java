package com.social.file_service.command;

import com.social.file_service.exception.FileSaveException;

abstract class CommandExecutor {

    protected CommandExecutor() {
    }

    public void execute() {
        this.preExecute();
        this.onExecute();
        this.postExecute();
    }

    protected abstract void onExecute() throws FileSaveException;

    protected abstract void preExecute();

    protected abstract void postExecute();
}
