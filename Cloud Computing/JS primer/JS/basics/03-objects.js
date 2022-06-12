// berisi related data dan functionality

const patient = {    
    userId: 1234,
    userComplain: {
        keluhan1: "pusing",
        keluhan2: "mual",
    },
    lamaWaktu: 3,
    addExtraLife() {
        this.lamaWaktu++;
    },
    printInfo () {
        // this = patient
        console.log(`Pasien dengan keluhan ${this.userComplain.keluhan1} telah dirasakan selama ${this.lamaWaktu} hari`)
    }
};

console.log(`Pasien dengan ID ${patient["userId"]} mempunyai keluhan ${patient.userComplain.keluhan2} selama kurang lebih ${patient.lamaWaktu} hari`);
patient.addExtraLife();
patient.printInfo();

// classes
class Dokter {
    constructor(name, poli) {
        this.name = name;
        this.poli = poli;
    }

    greet() {
        console.log(`Dokter ${this.name} bertugas di poli ${this.poli}`);
    }
}

const brandy = new Dokter("Brandy", "Gigi");
brandy.greet()
