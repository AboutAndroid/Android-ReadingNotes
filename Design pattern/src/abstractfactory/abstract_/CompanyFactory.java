package abstractfactory.abstract_;

public interface CompanyFactory {

	Store createStore();
	Checkstand createCheckstand();
	Tableware createTableware();
}
