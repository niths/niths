package no.niths.application.rest.lists.battlestation;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.AppNames;
import no.niths.domain.battlestation.Loan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = AppNames.LOANS)
public class LoanList extends ListAdapter<Loan> {

    private static final long serialVersionUID = 6932632501082726953L;

    @SuppressWarnings("unused")
    @XmlElement(name = "loan")
    private List<Loan> data;

    @Override
    public void setData(List<Loan> loans) {
        this.data = loans;
    }
}
