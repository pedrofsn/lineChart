package br.ufg.labtime.app.linechart;

import java.util.List;

public class MyData {

    private List<MyDataItem> grafico;

    public MyData(List<MyDataItem> grafico) {
        this.grafico = grafico;
    }

    public List<MyDataItem> getGrafico() {
        return grafico;
    }

    public void setGrafico(List<MyDataItem> grafico) {
        this.grafico = grafico;
    }
}
