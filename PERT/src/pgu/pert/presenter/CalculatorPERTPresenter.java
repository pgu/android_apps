package pgu.pert.presenter;

public interface CalculatorPERTPresenter {

    void setResult(Float result);

    Float getOptimisticValue();

    Float getNominalValue();

    Float getPessimisticValue();

}
