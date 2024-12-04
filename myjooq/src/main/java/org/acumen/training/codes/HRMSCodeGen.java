package org.acumen.training.codes;

import java.nio.file.Files;
import java.nio.file.Path;

import org.jooq.codegen.GenerationTool;

public class HRMSCodeGen {
	
	public static void main(String[] args) {
		try {
			GenerationTool.generate(Files.
					readString(Path.of("src/main/resources/jooq-config.xml")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
