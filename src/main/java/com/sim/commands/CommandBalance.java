package com.sim.commands;

import com.sim.Context;

public class CommandBalance extends BaseCommand {
    public CommandBalance() {
        description = "Check your financial status";
    }
    @Override
    public void execute(Context context, String command, String[] parameters) {
    // metode der fremvise din nuværende kampital
    context.GetBalance();
    }
}
