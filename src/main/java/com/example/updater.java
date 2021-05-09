package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class updater extends botmaker {
    public void isUpdate(Update update) {
        String msg = update.getMessage().toString();
        if (msg.equalsIgnoreCase("/update")) {
            runmsg(update.getMessage().getChatId().toString(), "Updating Bot Maker...");
            try {
                cloneRepository();
            } catch (GitAPIException | IOException e) {
                runmsg(update.getMessage().getChatId().toString(), "Cannot Update Bot!");
                e.printStackTrace();
            }
            runmsg(update.getMessage().getChatId().toString(),
                    "Bot Updated Successfully!!\nThanks to the Developer for such an update https://t.me/Hellion_OP");
        }
    }

    public void cloneRepository() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
        Git.cloneRepository().setURI("https://github.com/Hellboy-Aaryan/EvilX.git")
                .setDirectory(new File("E:/Telegram_Bot_JAVA/botmaker")).setCloneAllBranches(true).call();
    }

    public static void createLocalRepo() throws IOException, IllegalStateException, GitAPIException {
        File localPath = File.createTempFile("Testing", "");
        Files.delete(localPath.toPath());
        try (Git git = Git.init().setDirectory(localPath).call()) {
            System.out.println("Created repository: " + git.getRepository().getDirectory());
            File myFile = new File(git.getRepository().getDirectory().getParent(), "testfile");
            if (!myFile.createNewFile()) {
                throw new IOException("Could not create file " + myFile);
            }
        }
    }
}
