package buginvaderz.window;

import java.util.ArrayList;

public class Taskbar {
    ArrayList<IconTemplate> array = new ArrayList<>(5);

    public Taskbar() {

    }

    public void addIconTemplate(IconTemplate template) {
        array.add(template);
    }

    public void updateOrder(){
        for(IconTemplate sa:array){
            //ADD THE ICON TEMPLATE TO THE TASKBAR
        }
    }

    public IconTemplate getIconTemplate(int index) {
        return array.get(index);
    }
}
