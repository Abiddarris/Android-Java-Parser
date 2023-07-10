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

package test.javaparser.generics;

import test.javaparser.outsidepackage.OutsidePackage;

public class ClassTypeVariable2File<R, S extends R, T extends ClassTypeVariableFile<U,ClassTypeVariable3File<ClassFile,OutsidePackage>>, U extends OutsidePackage, V extends Runnable> {
    /*extends ClassTypeVariable4File<U>*/ /*    , W extends ClassTypeVariable4File<?>*/
    //ClassTypeVariable2File<R,T extends ClassTypeVariableFile<U,Runnable>>
}
