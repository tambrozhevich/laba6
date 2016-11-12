package com.belarus.minsk.bsu.famcs.ambrozhevich;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;


public class Main {
    private static final String[] poem = {
            "Ты меня не любишь, не жалеешь,",
            "Разве я немного не красив?",
            "Не смотря в лицо, от страсти млеешь,",
            "Мне на плечи руки опустив.",
            "",
            "Молодая, с чувственным оскалом,",
            "Я с тобой не нежен и не груб.",
            "Расскажи мне, скольких ты ласкала?",
            "Сколько рук ты помнишь? Сколько губ?",
            "",
            "Знаю я — они прошли, как тени,",
            "Не коснувшись твоего огня,",
            "Многим ты садилась на колени,",
            "А теперь сидишь вот у меня.",
            "",
            "Пуст твои полузакрыты очи",
            "И ты думаешь о ком-нибудь другом,",
            "Я ведь сам люблю тебя не очень,",
            "Утопая в дальнем дорогом.",
            "",
            "Этот пыл не называй судьбою,",
            "Легкодумна вспыльчивая связь,—",
            "Как случайно встретился с тобою,",
            "Улыбнусь, спокойно разойдясь.",
            "",
            "Да и ты пойдешь своей дорогой",
            "Распылять безрадостные дни,",
            "Только нецелованных не трогай,",
            "Только негоревших не мани.",
            "",
            "И когда с другим по переулку ",
            "Ты пойдешь, болтая про любовь,",
            "Может быть, я выйду на прогулку,",
            "И с тобою встретимся мы вновь.",
            "",
            "Отвернув к другому ближе плечи",
            "И немного наклонившись вниз,",
            "Ты мне скажешь тихо: «Добрый вечер...»",
            "Я отвечу: «Добрый вечер, miss».",
            "",
            "И ничто души не потревожит,",
            "И ничто ее не бросит в дрожь,—",
            "Кто любил, уж тот любить не может,",
            "Кто сгорел, того не подожжешь."
    };

    public static void main(String[] args) throws Exception {
        File root = new File("root.txt");
        BufferedReader reader = new BufferedReader(new FileReader(root));
        String dir = reader.readLine() + File.separator + reader.readLine();
        File rootDir = new File(dir);
        rootDir.mkdirs();
        int rootLevel = nestingLevel(rootDir);
        File file = new File("files.txt");
        reader = new BufferedReader(new FileReader(file));
        Collection<String> data = new LinkedList<>();
        while (reader.ready()) {
            data.add(reader.readLine());
        }

        for (String line : data) {
            File path = new File(dir + File.separator + line);
            if (!path.getAbsolutePath().matches(".+\\..+")){
                path.mkdirs();
                continue;
            }
            path.getParentFile().mkdirs();
            path.createNewFile();
            FileWriter writer = new FileWriter(path, false);
            int level = nestingLevel(path) - rootLevel;
            writer.write(path.getAbsolutePath() + System.lineSeparator());
            writer.write("" + level + System.lineSeparator());
            writer.write(poem[level%poem.length-1]);
            writer.flush();
        }
    }

    private static int nestingLevel(File file) {
        int level = 0;
        while (file.getParentFile() != null) {
            file = file.getParentFile();
            level++;
        }
        return level;
    }
}
