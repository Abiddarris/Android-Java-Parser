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

package com.abiddarris.javaparser.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Bracket {

    Bracket parent;
    List<Bracket> children = new ArrayList<>();
    int start;
    int end = -1;

    private Bracket() {}

    private Bracket(Bracket parent, int start) {
        this.parent = parent;
        parent.children.add(this);
        this.start = start;
    }
    
    private void end(int index) {
        if(end == -1) {
            end = index;
            return;
        }

        parent.end(index);
    }      
    
   /* private void print(StringBuilder builder, Bracket bracket, int indent) { 
        for(int i = 0; i < indent; i++) {
            System.out.print(" ");
        }

        System.out.println(bracket.start + ", " + bracket.end);
        for(Bracket Bracket : bracket.children) {
            if(Bracket != null)
                print(builder,Bracket,indent + 4);
        }

    }*/

    public static Bracket parse(String data, String start, String end) {
        Pattern pattern = Pattern.compile("[" + start + "|" + end + "]");
        Matcher matcher = pattern.matcher(data);
        
        Bracket parent = new Bracket();
        while(matcher.find()) {
            String character = matcher.group();
            if(character.equals(start)) {
                Bracket Bracket = new Bracket(parent,matcher.start());
                parent = Bracket;
            } else if(character.equals(end)) {
                parent.end(matcher.start());
                parent = parent.parent;
            }
        }
               
        return parent;
//        List<BracketChar> brackets = find(data,start,BracketChar.Type.START);
//        List<BracketChar> bracketEnds = find(data,end,BracketChar.Type.END);
//        
//        brackets.addAll(bracketEnds);
//        Collections.sort(brackets, new Comparator<BracketChar>() {
//                @Override
//                public int compare(BracketChar p1, BracketChar p2) {
//                    return Integer.compare(p1.index,p2.index);
//                }                      
//            });
//            
//        Bracket parent = new Bracket();
//        for(int i = 0; i < brackets.size(); i++) {         
//            BracketChar bracketChar = brackets.get(i);
//            if(bracketChar.type == BracketChar.Type.START) {
//                Bracket Bracket = new Bracket(parent,bracketChar.index);
//                parent = Bracket;
//            } else if(bracketChar.type == BracketChar.Type.END) {
//                parent.end(bracketChar.index);
//                parent = parent.parent;
//            }          
//        }
//        return parent;
    }            
      
    private static List<BracketChar> find(String data, String c, BracketChar.Type type) {
        List<BracketChar> locations = new ArrayList<>();

        int from = 0;       
        while ((from = data.indexOf(c, from)) != -1) {
            locations.add(new BracketChar(from,type));
            from++;
        }
        return locations;
    }  
    
    private static class BracketChar {

        private int index;
        private Type type;

        private BracketChar(int index, Type type) {
            this.index = index;
            this.type = type;
        }

        @Override
        public String toString() {
            return index + " " + type;
        }     

        private static enum Type {
            START,END
        }
    }
}
