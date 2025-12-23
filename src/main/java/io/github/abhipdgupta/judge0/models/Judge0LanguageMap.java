/* (C)2025 */
package io.github.abhipdgupta.judge0.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * this enum maps language to latest version of their compiler or interpreter
 */
@Getter
@AllArgsConstructor
public enum Judge0LanguageMap {
    C(103, "C (GCC 14.1.0)"),

    CPP(105, "C++ (GCC 14.1.0)"),

    GO(107, "Go (1.23.5)"),

    JAVA(91, "Java (JDK 17.0.6)"),

    JAVASCRIPT(102, "JavaScript (Node.js 22.08.0)"),

    PYTHON(109, "Python (3.13.2)"),

    TYPESCRIPT(101, "TypeScript (5.6.2)"),

    CHSARP(51, "C# (Mono 6.6.0.161)"),

    RUST(108, "Rust (1.85.0)"),

    MULTI_FILE(89, "Multi File Program");

    final int id;
    final String name;

    public static Judge0LanguageMap fromId(int id) {
        for (Judge0LanguageMap language : Judge0LanguageMap.values()) {
            if (language.id == id) {
                return language;
            }
        }
        return null;
    }
}
