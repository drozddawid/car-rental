package db2.carrental.controllers;

import db2.carrental.entities.*;
import db2.carrental.repositories.*;
import db2.carrental.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//TODO: Verify permission level for every method
@RestController
@RequestMapping(path = "/data")
public class DataController {

    private final DataService dataService;
    private final KlienciRepository klienciRepository;
    private final LogWypozyczeniaRepository logWypozyczeniaRepository;
    private final ModeleRepository modeleRepository;
    private final SamochodyRepository samochodyRepository;
    private final UzytkownicyRepository uzytkownicyRepository;
    private final WypozyczalnieRepository wypozyczalnieRepository;
    private final WypozyczeniaRepository wypozyczeniaRepository;
    private final DostepneSamochodyRepository dostepneSamochodyRepository;
    private final NiedostepneSamochodyRepository niedostepneSamochodyRepository;
    private final WypozyczoneSamochodyRepository wypozyczoneSamochodyRepository;

    @Autowired
    public DataController(DataService dataService, KlienciRepository klienciRepository, LogWypozyczeniaRepository logWypozyczeniaRepository, ModeleRepository modeleRepository, SamochodyRepository samochodyRepository, UzytkownicyRepository uzytkownicyRepository, WypozyczalnieRepository wypozyczalnieRepository, WypozyczeniaRepository wypozyczeniaRepository, DostepneSamochodyRepository dostepneSamochodyRepository, NiedostepneSamochodyRepository niedostepneSamochodyRepository, WypozyczoneSamochodyRepository wypozyczoneSamochodyRepository) {
        this.dataService = dataService;
        this.klienciRepository = klienciRepository;
        this.logWypozyczeniaRepository = logWypozyczeniaRepository;
        this.modeleRepository = modeleRepository;
        this.samochodyRepository = samochodyRepository;
        this.uzytkownicyRepository = uzytkownicyRepository;
        this.wypozyczalnieRepository = wypozyczalnieRepository;
        this.wypozyczeniaRepository = wypozyczeniaRepository;
        this.dostepneSamochodyRepository = dostepneSamochodyRepository;
        this.niedostepneSamochodyRepository = niedostepneSamochodyRepository;
        this.wypozyczoneSamochodyRepository = wypozyczoneSamochodyRepository;
    }




    @GetMapping("/wypozyczonesamochody")
    public ResponseEntity<Object> getAllRentedCars(@RequestParam String authToken) {
        return dataService.getAll(wypozyczoneSamochodyRepository, authToken, 0);
    }

    @GetMapping("/niedostepnesamochody")
    public ResponseEntity<Object> getAllUnvailableCars(@RequestParam String authToken) {
        return dataService.getAll(niedostepneSamochodyRepository, authToken, 0);
    }

    @GetMapping("/dostepnesamochody")
    public ResponseEntity<Object> getAllAvailableCars(@RequestParam String authToken) {
        return dataService.getAll(dostepneSamochodyRepository, authToken, 0);
    }
    @GetMapping("/samochody")
    public ResponseEntity<Object> getAllCars(@RequestParam String authToken) {
        return dataService.getAll(samochodyRepository, authToken, 1);
    }

    @GetMapping("/klienci")
    public ResponseEntity<Object> getAllClients(@RequestParam String authToken) {
        return dataService.getAll(klienciRepository, authToken, 1);
    }

    @GetMapping("/logwypozyczenia")
    public ResponseEntity<Object> getAllLogWypozyczenia(@RequestParam String authToken) {
        return dataService.getAll(logWypozyczeniaRepository, authToken, 1);
    }

    @GetMapping("/modele")
    public ResponseEntity<Object> getAllModels(@RequestParam String authToken) {
        return dataService.getAll(modeleRepository, authToken, 1);
    }

    @GetMapping("/uzytkownicy")
    public ResponseEntity<Object> getAllUsers(@RequestParam String authToken) {
        return dataService.getAll(uzytkownicyRepository, authToken, 1);
    }

    @GetMapping("/wypozyczalnie")
    public ResponseEntity<Object> getAllRent(@RequestParam String authToken) {
        return dataService.getAll(wypozyczalnieRepository, authToken, 1);
    }

    @GetMapping("/wypozyczenia")
    public ResponseEntity<Object> getAllRentals(@RequestParam String authToken) {
        return dataService.getAll(wypozyczeniaRepository, authToken, 1);
    }

    //--------------------------------------------------
    @GetMapping("uzytkownicy/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(uzytkownicyRepository, id, authToken, false, 1);
    }

    @GetMapping("modele/{id}")
    public ResponseEntity<Object> getOneModel(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(modeleRepository, id, authToken, false, 1);
    }

    @GetMapping("wypozyczalnie/{id}")
    public ResponseEntity<Object> getOneRent(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(wypozyczalnieRepository, id, authToken, true, 1);
    }

    @GetMapping("samochody/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(samochodyRepository, id, authToken, true, 1);
    }

    @GetMapping("logwypozyczenia/{id}")
    public ResponseEntity<Object> getOneLogWypozyczenia(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(logWypozyczeniaRepository, id, authToken, true, 1);
    }

    @GetMapping("wypozyczenia/{id}")
    public ResponseEntity<Object> getOneRental(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(wypozyczeniaRepository, id, authToken, true, 1);
    }

    @GetMapping("klienci/{id}")
    public ResponseEntity<Object> getOneCustomer(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.getOne(klienciRepository, id, authToken, true, 1);
    }

    //------------------------------------------------
    @DeleteMapping("uzytkownicy/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(uzytkownicyRepository, id, authToken, false, 1);
    }

    @DeleteMapping("modele/{id}")
    public ResponseEntity<Object> deleteModel(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(modeleRepository, id, authToken, false, 1);
    }

    @DeleteMapping("wypozyczalnie/{id}")
    public ResponseEntity<Object> deleteRent(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(wypozyczalnieRepository, id, authToken, true, 1);
    }

    @DeleteMapping("samochody/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(samochodyRepository, id, authToken, true, 1);
    }

    @DeleteMapping("logwypozyczenia/{id}")
    public ResponseEntity<Object> deleteLogWypozyczenia(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(logWypozyczeniaRepository, id, authToken, true, 1);
    }

    @DeleteMapping("wypozyczenia/{id}")
    public ResponseEntity<Object> deleteRental(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(wypozyczeniaRepository, id, authToken, true, 1);
    }

    @DeleteMapping("klienci/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") String id, @RequestParam String authToken) {
        return dataService.deleteOne(klienciRepository, id, authToken, true, 1);
    }

    //--------------------------------
    @PostMapping("/samochody")
    public ResponseEntity<Object> postCar(@RequestBody Samochody samochody, @RequestParam String authToken) {
        List<Samochody> carsWithTheSameLicensePlate = samochodyRepository.findBynrRejestracyjny(samochody.getNrRejestracyjny());
        if(carsWithTheSameLicensePlate != null && carsWithTheSameLicensePlate.size() != 0){
            Map<String, Object> resp = new LinkedHashMap<>();
            resp.put("status", "error");
            resp.put("answer", "Entity with the same license plate exists.");
            return ResponseEntity.badRequest().body(resp);
        }else{
        return dataService.post(samochodyRepository, samochody, authToken, 1);
        }
    }

    @PostMapping("/klienci")
    public ResponseEntity<Object> postClients(@RequestBody Klienci klienci, @RequestParam String authToken) {
        return dataService.post(klienciRepository, klienci, authToken, 1);
    }

    @PostMapping("/logwypozyczenia")
    public ResponseEntity<Object> postLogWypozyczenia(@RequestBody LogWypozyczenia logWypozyczenia, @RequestParam String authToken) {
        return dataService.post(logWypozyczeniaRepository, logWypozyczenia, authToken, 1);
    }

    @PostMapping("/modele")
    public ResponseEntity<Object> postModels(@RequestBody Modele modele, @RequestParam String authToken) {
        return dataService.post(modeleRepository, modele, authToken, 1);
    }

    @PostMapping("/uzytkownicy")
    public ResponseEntity<Object> postUser(@RequestBody Uzytkownicy uzytkownicy, String authToken) {
        return dataService.post(uzytkownicyRepository, uzytkownicy, authToken, 1);
    }

    @PostMapping("/wypozyczalnie")
    public ResponseEntity<Object> postRent(@RequestBody Wypozyczalnie wypozyczalnie, @RequestParam String authToken) {
        return dataService.post(wypozyczalnieRepository, wypozyczalnie, authToken, 1);
    }

    @PostMapping("/wypozyczenia")
    public ResponseEntity<Object> postRentals(@RequestBody Wypozyczenia wypozyczenia, @RequestParam String authToken) {
        return dataService.post(wypozyczeniaRepository, wypozyczenia, authToken, 1);
    }
    //---------------------------------------------
    @PutMapping("/uzytkownicy/{id}")
    public ResponseEntity<Object> putUser(@RequestBody Uzytkownicy entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(uzytkownicyRepository, entity, id, authToken, false, 1);
    }

    @PutMapping("/modele/{id}")
    public ResponseEntity<Object> putModel(@RequestBody Modele entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(modeleRepository, entity, id, authToken, false, 1);
    }

    @PutMapping("/samochody/{id}")
    public ResponseEntity<Object> putCar(@RequestBody Samochody entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(samochodyRepository, entity, id, authToken, true, 1);
    }

    @PutMapping("/klienci/{id}")
    public ResponseEntity<Object> putCustomer(@RequestBody Klienci entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(klienciRepository, entity, id, authToken, true, 1);
    }
    @PutMapping("/logwypozyczenia/{id}")
    public ResponseEntity<Object> putLog(@RequestBody LogWypozyczenia entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(logWypozyczeniaRepository, entity, id, authToken, true, 1);
    }
    @PutMapping("/wypozyczenia/{id}")
    public ResponseEntity<Object> putRental(@RequestBody Wypozyczenia entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(wypozyczeniaRepository, entity, id, authToken, true, 1);
    }
    @PutMapping("/wypozyczalnie/{id}")
    public ResponseEntity<Object> putRentPlace(@RequestBody Wypozyczalnie entity, @PathVariable String id, @RequestParam String authToken){
        return dataService.put(wypozyczalnieRepository, entity, id, authToken, true, 1);
    }
}
