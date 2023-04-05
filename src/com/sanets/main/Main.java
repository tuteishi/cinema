package com.sanets.main;

import controller.PersonController;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        PersonController personController = new PersonController();
        personController.enterInSystem();
    }
}