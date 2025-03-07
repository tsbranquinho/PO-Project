package xxl;

import java.io.Serializable;

import java.util.ArrayList;

import xxl.content.Content;
import xxl.content.LiteralContent;
import xxl.content.ReferenceContent;
import xxl.visitor.VisitorEvaluate;
import xxl.observer.Observer;
import xxl.observer.Subject;


public class Cell implements Serializable, Observer, Subject{

    private Content _content;
    private Boolean _inicialize = false;
    private ArrayList<Observer> _dependents = new ArrayList<>();
    
    public Cell() {
        _content = new Content();
    }

    public Boolean getInicialize() {
        return _inicialize;
    }

    public void setInicialize() {
        _inicialize = true;
    }

    public void addObserver(Cell cell) {
        _dependents.add(cell);
    }

    public void removeObserver(Cell cell) {
        _dependents.remove(cell);
    }

    public void updateObservers() {
        for (Observer observer : _dependents) {
            observer.evaluateCell();
        }
    }

    public void deleteContent() {
        _content = new Content();
    }

    public void evaluateCell() {
        
        String value;
        value = _content.accept(new VisitorEvaluate());
        if (value == null && _content.getValue() == null) {
            // if it's null there's no need to update anything or set value
            return;
        }
        _content.setValue(value);
        updateObservers();
    }
    
    public Content getContent() {
        return _content;
    }

    public void setContent(Content content) {
        _content = content;
    }

    public void setContent(String value, Cell cell) {
        _content = (ReferenceContent) new ReferenceContent(value, cell);
    }

    public void setContent(String value) {
        _content = (LiteralContent) new LiteralContent(value);
    }

    @Override
    public String toString() {

        if (_content == null) {
            return "|";
        }
        
        else {
            return "|" + _content.toString();
        }
    }
}