/*
 * Экземпляр этого класса будет подаваться для сохранения резмеров и положения окно
 * а также этот клас будем результатом считывания
 */
package ua.edu.odeku.pet.settings;

/**
 *
 * @author Aleo
 */
public class DimensionEntry {
    String name;
    public int x , y, width, height;
    
    public DimensionEntry(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public DimensionEntry(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public DimensionEntry() {
    }
    
}
