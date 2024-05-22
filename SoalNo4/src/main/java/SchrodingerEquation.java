import java.util.ArrayList;
import java.util.List;

// Definisi kelas untuk menyelesaikan persamaan Schrödinger
class SchrodingerEquation {
    private double potentialEnergy; // Energi potensial (V)
    private double totalEnergy;     // Energi total (E)
    private double mass;            // Massa partikel (m)
    private double position;        // Posisi (x)
    private double time;            // Waktu (t)
    private double hBar;            // Konstanta Planck tereduksi (ħ)
    private double waveNumber;      // Bilangan gelombang (k)
    private List<Double> potentialEnergyProfile; // Profil energi potensial

    // Constructor untuk persamaan Schrödinger bebas waktu
    public SchrodingerEquation(double potentialEnergy, double totalEnergy, double mass, double position) {
        this.potentialEnergy = potentialEnergy;
        this.totalEnergy = totalEnergy;
        this.mass = mass;
        this.position = position;
        this.hBar = 1.0545718e-34; // Konstanta Planck tereduksi dalam satuan SI
        this.waveNumber = calculateWaveNumber(totalEnergy, potentialEnergy, mass); // Menghitung bilangan gelombang (k)
        this.potentialEnergyProfile = new ArrayList<>(); // Inisialisasi list untuk profil energi potensial
    }

    // Constructor untuk persamaan Schrödinger tergantung waktu
    public SchrodingerEquation(double potentialEnergy, double totalEnergy, double mass, double position, double time) {
        this(potentialEnergy, totalEnergy, mass, position); // Memanggil constructor bebas waktu
        this.time = time; // Menetapkan waktu (t)
    }

    // Method untuk menghitung bilangan gelombang (k)
    private double calculateWaveNumber(double totalEnergy, double potentialEnergy, double mass) {
        return Math.sqrt(2 * mass * (totalEnergy - potentialEnergy)) / hBar;
    }

    // Method untuk menghitung fungsi gelombang bebas waktu
    public double waveFunction(double position) {
        return Math.sin(waveNumber * position);
    }

    // Overloading method untuk menghitung fungsi gelombang tergantung waktu
    public double waveFunction(double position, double time) {
        return Math.sin(waveNumber * position) * Math.cos(totalEnergy * time / hBar);
    }

    // Method untuk menambah profil energi potensial
    public void addPotentialEnergyProfile(double potentialEnergy) {
        this.potentialEnergyProfile.add(potentialEnergy);
    }

    // Method untuk mendapatkan profil energi potensial
    public List<Double> getPotentialEnergyProfile() {
        return potentialEnergyProfile;
    }

    // Method untuk menghitung operator Hamiltonian
    public double hamiltonianOperator(double position, double time) {
        double kineticEnergy = - (hBar * hBar / (2 * mass)) * (waveNumber * waveNumber); // Menghitung energi kinetik
        double potentialEnergy = getPotentialEnergyAtPosition(position); // Mendapatkan energi potensial pada posisi tertentu
        return kineticEnergy + potentialEnergy;
    }

    // Method untuk mendapatkan energi potensial pada posisi tertentu
    private double getPotentialEnergyAtPosition(double position) {
        int index = (int) Math.round(position);
        if (index >= 0 && index < potentialEnergyProfile.size()) {
            return potentialEnergyProfile.get(index);
        } else {
            return this.potentialEnergy; // Default energi potensial jika posisi di luar jangkauan profil
        }
    }

    // Method untuk menampilkan informasi persamaan
    public void displayInfo() {
        System.out.println("Potential Energy: " + potentialEnergy);
        System.out.println("Total Energy: " + totalEnergy);
        System.out.println("Mass: " + mass);
        System.out.println("Position: " + position);
        System.out.println("Planck Constant (h-bar): " + hBar);
        System.out.println("Wave Number: " + waveNumber);
        if (time != 0) {
            System.out.println("Time: " + time);
        }
    }
}