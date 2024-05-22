import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

// Kelas untuk visualisasi persamaan Schrödinger menggunakan JavaFX
public class SchrodingerVisualizer extends Application {
    @Override
    public void start(Stage primaryStage) {
        SchrodingerEquation schrodinger = new SchrodingerEquation(10, 20, 1.0, 2.0);

        // Tambahkan beberapa profil energi potensial untuk visualisasi
        for (int i = 0; i < 10; i++) {
            schrodinger.addPotentialEnergyProfile(10 + i * 2);
        }

        NumberAxis xAxis = new NumberAxis(); // Sumbu X untuk grafik fungsi gelombang
        NumberAxis yAxis = new NumberAxis(); // Sumbu Y untuk grafik fungsi gelombang
        LineChart<Number, Number> waveFunctionChart = new LineChart<>(xAxis, yAxis);
        waveFunctionChart.setTitle("Wave Function");

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Time-independent Wave Function");

        // Plot fungsi gelombang
        for (int i = 0; i <= 100; i++) {
            double position = i * 0.1;
            double value = schrodinger.waveFunction(position);
            series1.getData().add(new XYChart.Data<>(position, value));
        }

        waveFunctionChart.getData().add(series1);

        // Chart untuk profil energi potensial
        NumberAxis xAxis2 = new NumberAxis(); // Sumbu X untuk grafik energi potensial
        NumberAxis yAxis2 = new NumberAxis(); // Sumbu Y untuk grafik energi potensial
        LineChart<Number, Number> potentialEnergyChart = new LineChart<>(xAxis2, yAxis2);
        potentialEnergyChart.setTitle("Potential Energy Profile");

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Potential Energy");

        // Plot profil energi potensial
        List<Double> potentialProfile = schrodinger.getPotentialEnergyProfile();
        for (int i = 0; i < potentialProfile.size(); i++) {
            series2.getData().add(new XYChart.Data<>(i, potentialProfile.get(i)));
        }

        potentialEnergyChart.getData().add(series2);

        VBox vbox = new VBox(waveFunctionChart, potentialEnergyChart); // Menambahkan grafik ke dalam VBox

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Schrodinger Equation Visualizer");
        primaryStage.show();
    }
}