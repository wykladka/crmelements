package pl.waw.azymut.models.comparerkiller.ceneo.entity;

import pl.waw.azymut.models.general.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class BindingGroup extends BaseEntity {

    //todo annotacje hibernata 

    private String bindingGroupName;

    @OneToMany
    private List<BindingForOwnProductWithMultipleCeneos> bindingList;

    @OneToOne //todo many to one?
    private BindingRule bindingRule;

    public String getBindingGroupName() {
        return bindingGroupName;
    }

    public void setBindingGroupName(String bindingGroupName) {
        this.bindingGroupName = bindingGroupName;
    }

    public List<BindingForOwnProductWithMultipleCeneos> getBindingList() {
        return bindingList;
    }

    public void setBindingList(List<BindingForOwnProductWithMultipleCeneos> bindingList) {
        this.bindingList = bindingList;
    }

    public BindingRule getBindingRule() {
        return bindingRule;
    }

    public void setBindingRule(BindingRule bindingRule) {
        this.bindingRule = bindingRule;
    }

    public BindingGroup() {
    }
}
