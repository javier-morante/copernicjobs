package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.offers.CreateOfferDTO;
import cat.copernic.backend.data.models.offer.OfferRepository;
import cat.copernic.backend.data.models.offer.OfferServiceImpl;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.offers.OffersService;

@RestController
@RequestMapping("/api/offers")
public class OffersController {
    
    @Autowired
    OffersService offersService;

    @Autowired
    OfferServiceImpl test;

    @Autowired
    OfferRepository test2;

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOffers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = offersService.getAllOffers(auth);

        return ResponseEntity.status(200).body(
            res.getBody()
        );
    }


    @PostMapping("/create-offer")
    public ResponseEntity<?> createOffer(
        @RequestBody CreateOfferDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.offersService.createOffer(auth, body);

        if(res.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                "OK"
            );
        }

        return ResponseEntity.status(400).body(
            res.getBody()
        );
    }

    @GetMapping("/subscribe")
    public ResponseEntity<?> subscribeStudent(@RequestParam Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Response res = offersService.subscribeStudent(auth, id);
        if (res.getStatus() == ResponseState.ERROR) {
            return ResponseEntity.status(400).body(res.getBody());
        }  else {
            return (ResponseEntity.status(200).body(res.getBody()));
        }
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribeStudent(@RequestParam Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Response res = offersService.unsubscribeStudent(auth, id);

        return ResponseEntity.status(200).body(res.getBody());
    }

    @GetMapping("/get-subscribed-offers")
    public ResponseEntity<?> getSubscribedOffers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Response res = offersService.getSubscribedOffers(auth);
        
        return ResponseEntity.status(200).body(res.getBody());
    }

    @GetMapping("/get-subscribed-students")
    public ResponseEntity<?> getSubscribedStudents(@RequestParam Integer offerId) {
        Response res = offersService.getSubscribedStudents(offerId);

        return ResponseEntity.status(200).body(res.getBody());
    }

    @PostMapping("/update-offer")
    public ResponseEntity<?> updateOffer(
        @RequestBody CreateOfferDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.offersService.updateOffer(auth, body);

        if(res.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                "OK"
            );
        }

        return ResponseEntity.status(400).body(
            res.getBody()
        );
    }

    @GetMapping("/get-user-id")
    public ResponseEntity<?> getCompanyId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.status(200).body(
            this.userService.getByEmail(auth.getName()).getNif()
        );
    }


    @GetMapping("/update-offer-status")
    public ResponseEntity<?> disableOffer(
        @RequestParam Integer offerId
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.offersService.disableOffer(auth, offerId);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        
        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }
}