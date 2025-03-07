package xxl.observer;

import xxl.Cell;

public interface Subject {
    public void addObserver(Cell cell);
    public void removeObserver(Cell cell);
    public void updateObservers();
}