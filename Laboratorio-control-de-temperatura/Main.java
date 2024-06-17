// Define la interfaz Observer
interface Observer {
    void update(int temperature);
}

// Define la interfaz Subject
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Implementa la clase WeatherStation que sigue la interfaz Subject
import java.util.ArrayList;
import java.util.List;

class WeatherStation implements Subject {
    private List<Observer> observers;
    private int temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

// Implementa la clase TemperatureDisplay que sigue la interfaz Observer
class TemperatureDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("TemperatureDisplay: La temperatura actual es " + temperature + "°C");
    }
}

// Implementa la clase WeatherWarning que sigue la interfaz Observer
class WeatherWarning implements Observer {
    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            System.out.println("WeatherWarning: ¡Advertencia! La temperatura ha alcanzado " + temperature + "°C");
        }
    }
}

// Clase principal para probar el sistema
public class Main {
    public static void main(String[] args) {
        // Crea una instancia de WeatherStation
        WeatherStation station = new WeatherStation();
        
        // Crea instancias de los observadores
        TemperatureDisplay display = new TemperatureDisplay();
        WeatherWarning warning = new WeatherWarning();
        
        // Agrega los observadores a la estación meteorológica
        station.addObserver(display);
        station.addObserver(warning);
        
        // Cambia la temperatura de la estación y notifica a los observadores
        System.out.println("Cambiando la temperatura a 25°C...");
        station.setTemperature(25);
        
        System.out.println("Cambiando la temperatura a 35°C...");
        station.setTemperature(35);
    }
}
