package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateLangEntries;
import com.sudoplay.mc.kor.core.generation.annotation.KorLangEntry;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sk3lls on 11/8/2016.
 */
public class LangEntriesGenerator extends
    AbstractAssetGenerator<KorGenerateLangEntries> {

  private Map<File, StringBuilder> builderMap;
  private String langPath;

  public LangEntriesGenerator(String langPath) {
    this.langPath = langPath;
    this.builderMap = new HashMap<>();
  }

  @Override
  public void generate(KorGenerateLangEntries annotation) {

    for (KorLangEntry entry : annotation.entries()) {

      String file = entry.file();
      String key = entry.key();
      String value = entry.value();

      File actualFile = new File(new File(this.langPath, "lang"), file);
      StringBuilder stringBuilder = this.builderMap.get(actualFile);

      if (stringBuilder == null) {
        stringBuilder = new StringBuilder();
        this.builderMap.put(actualFile, stringBuilder);
      }

      stringBuilder.append(key).append("=").append(value).append("\n");
    }
  }

  public void flush() {

    for (File file : this.builderMap.keySet()) {
      StringBuilder stringBuilder = this.builderMap.get(file);
      this.writeFile(stringBuilder.toString(), file);
    }
    this.builderMap.clear();
  }
}
