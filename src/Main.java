/*******************************************************************************
 * Copyright 2023 by Abiddarris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import java.lang.annotation.Annotation;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.javaparser.JavaParserTest;
import test.javaparser.classes.DeclaredClassesTest;
import test.javaparser.generics.interfaces.GenericInterfaceTest;
import test.javaparser.generics.superclasses.GenericSuperclassTest;
import test.javaparser.modifiers.ModifierTest;
import test.javaparser.generics.GenericTest;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {                
        System.out.println(run(JavaParserTest.class, ModifierTest.class, 
            DeclaredClassesTest.class, GenericInterfaceTest.class, GenericSuperclassTest.class, 
            GenericTest.class));
    }
    
    public static final String run(Class... classes){
        Result result = JUnitCore.runClasses(classes);

        StringBuilder builder = new StringBuilder()
            .append("Run Count : ")
            .append(result.getRunCount())
            .append("\nFailure Count : ")
            .append(result.getFailureCount())
            .append("\nRun Time : ")
            .append(result.getRunTime())
            .append("\nIgnore Count : ")
            .append(result.getIgnoreCount())
            .append("\nAssumption Failure Count : ")
            .append(result.getAssumptionFailureCount())
            .append("\nSuccessful : ")
            .append(result.wasSuccessful())
            .append("\nFailures : ");

        for(Failure failure : result.getFailures()){
            Description description = failure.getDescription();

            builder.append("\n\tTest Header : ")
                .append(failure.getTestHeader())
                .append("\n\tDescription : ")
                .append("\n\t\tDisplay Name : ")
                .append(description.getDisplayName())
                .append("\n\t\tIs Suite : ")
                .append(description.isSuite())
                .append("\n\t\tIs Test : ")
                .append(description.isTest())
                .append("\n\t\tTest Count : ")
                .append(description.testCount())
                .append("\n\t\tTest Class : ")
                .append(description.getTestClass())
                .append("\n\t\tClass Name : ")
                .append(description.getClassName())
                .append("\n\t\tMethod Name : ")
                .append(description.getMethodName())
                .append("\n\t\tAnnotations : ");
            for(Annotation annotation : description.getAnnotations()){
                builder.append("\n\t\t\t")
                    .append(annotation);
            } 

            String trace = failure.getTrace();
            trace = trace.substring(0,trace.indexOf("at java.lang.reflect.Method.invoke(Native Method)"));
            builder.append("\n\tTrace : ")
                .append(trace);
        }

        return builder.toString();              
    }
    
    
}
