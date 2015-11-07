package com.boldijarpaul.itfest;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class EventModelsTests {

    // something similar to - http://api.popula.de/api/mobilejson_2_1/event?pkey=195dd2b8ba6f4cb0d0165014510f36cc&eventid=12116535&terminoffice_id=21
    private String eventJsonMock = "{\"event\":{\"id\":\"12116535\",\"title\":\"Sprittwoch - Helau Special\",\"subtitle\":\"Hip Hop, Rock, eine Prise Elektro, \\u201eKarnevals Smash Hits\\u201c & 90er Jahre Trash\",\"shortdescription\":\"SPRITTWOCH - Helau Special Hip Hop, Rock, eine Prise Elektro, \\u201eKarnevals Smash Hits\\u201c & 90er Trash\",\"description\":\"DESCRIPTION\",\"descriptionteaser\":\"dt\",\"link\":\"www.facebook.com\\/schickimicki\",\"url\":\"http:\\/\\/termine.rp-online.de\\/veranstaltung\\/12116535_sprittwoch-helau-special-schickimicki-duesseldorf\",\"tinyurl\":\"http:\\/\\/termine.rp-online.de\\/077p6f\",\"date\":{\"date\":\"Mittwoch, 11.11.2015 21:00\",\"weekday\":\"Mi\"},\"tag\":{\"id\":\"196885\",\"name\":\"Bier 1 \\u20ac\"},\"location\":{\"id\":\"81405\",\"name\":\"Schickimicki\",\"url\":\"http:\\/\\/termine.rp-online.de\\/duesseldorf\\/81405_schickimicki-duesseldorf\",\"descriptionteaser\":\"dt\",\"link\":\"www.facebook.com\\/schickimicki\",\"tel\":\"0211-135034\",\"fax\":\"\",\"street\":\"Neustra\\u00dfe 51\",\"postcode\":\"40213\",\"latitude\":\"51.2258268\",\"longitude\":\"6.7760529\",\"city\":{\"id\":\"104\",\"url\":\"duesseldorf\",\"name\":\"D\\u00fcsseldorf\"}},\"image\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/event\\/012116\\/sprittwoch-helau-special-schickimicki-duesseldorf_12116535.jpg\",\"image_57\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/event\\/012116\\/thumbnail\\/sprittwoch-helau-special-schickimicki-duesseldorf_12116535_57.jpg\",\"image_175\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/event\\/012116\\/thumbnail\\/sprittwoch-helau-special-schickimicki-duesseldorf_12116535_175.jpg\",\"image_320\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/event\\/012116\\/thumbnail\\/sprittwoch-helau-special-schickimicki-duesseldorf_12116535_320.jpg\",\"type\":[{\"id\":\"41\",\"name\":\"Konzerte & Nachtleben\"},{\"id\":\"59\",\"name\":\"90s\"},{\"id\":\"53\",\"name\":\"Rock\"},{\"id\":\"52\",\"name\":\"RnB & HipHop\"}],\"sortvalue\":\"48\",\"user\":{\"id\":\"62521\",\"email\":\"schickimickiclub@gmx.de\",\"company\":\"Schickimicki\",\"firstname\":\"Daniel\",\"lastname\":\"Vollmer\",\"displayusername\":\"Schickimicki\",\"countfriends\":0,\"counteventabo\":0,\"countlocationabo\":0,\"counttopicabo\":0},\"ticket\":[],\"checkinuser\":[],\"topics\":[],\"comments\":[],\"userimages\":[]}}";
    private String userJsonMock = "{\"user\":{\"id\":\"1315\",\"email\":\"gp@popula.de\",\"nickname\":\"hg\",\"firstname\":\"XTRA\",\"lastname\":\"Redaktion\",\"description\":\"hallo ich bin gregor\",\"image\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/user\\/000001\\/gp-popula-de_1315.jpg\",\"image_300\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/user\\/000001\\/thumbnail\\/gp-popula-de_1315_300.jpg\",\"image_320\":\"http:\\/\\/s3-eu-west-1.amazonaws.com\\/popula\\/user\\/000001\\/thumbnail\\/gp-popula-de_1315_320.jpg\",\"displayusername\":\"hg\",\"countfriends\":1,\"counteventabo\":3,\"countlocationabo\":0,\"counttopicabo\":0}}";

    @Test
    public void eventModelPrimitiveVariablesTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;

        assertEquals(eventModel.id, 12116535);
        assertEquals(eventModel.title, "Sprittwoch - Helau Special");
        assertEquals(eventModel.subtitle, "Hip Hop, Rock, eine Prise Elektro, „Karnevals Smash Hits“ & 90er Jahre Trash");
        assertEquals(eventModel.shortDescription, "SPRITTWOCH - Helau Special Hip Hop, Rock, eine Prise Elektro, „Karnevals Smash Hits“ & 90er Trash");
        assertEquals(eventModel.description, "DESCRIPTION");
        assertEquals(eventModel.descriptionTeaser, "dt");
        assertEquals(eventModel.link, "www.facebook.com/schickimicki");
        assertEquals(eventModel.url, "http://termine.rp-online.de/veranstaltung/12116535_sprittwoch-helau-special-schickimicki-duesseldorf");
        assertEquals(eventModel.tinyurl, "http://termine.rp-online.de/077p6f");
        assertEquals(eventModel.image, "http://s3-eu-west-1.amazonaws.com/popula/event/012116/sprittwoch-helau-special-schickimicki-duesseldorf_12116535.jpg");
        assertEquals(eventModel.image57, "http://s3-eu-west-1.amazonaws.com/popula/event/012116/thumbnail/sprittwoch-helau-special-schickimicki-duesseldorf_12116535_57.jpg");
        assertEquals(eventModel.image175, "http://s3-eu-west-1.amazonaws.com/popula/event/012116/thumbnail/sprittwoch-helau-special-schickimicki-duesseldorf_12116535_175.jpg");
        assertEquals(eventModel.image320, "http://s3-eu-west-1.amazonaws.com/popula/event/012116/thumbnail/sprittwoch-helau-special-schickimicki-duesseldorf_12116535_320.jpg");
        assertEquals(eventModel.sortValue, 48);

    }

    @Test
    public void eventModelDateTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;

        assertEquals(eventModel.dateModel.date, "Mittwoch, 11.11.2015 21:00");
        assertEquals(eventModel.dateModel.weekday, "Mi");
    }

    @Test
    public void eventModelTagTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;

        assertEquals(eventModel.tagModel.id, 196885);
        assertEquals(eventModel.tagModel.name, "Bier 1 €");
    }

    @Test
    public void eventModelLocationTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;

        assertEquals(eventModel.locationModel.id, 81405);
        assertEquals(eventModel.locationModel.name, "Schickimicki");
        assertEquals(eventModel.locationModel.url, "http://termine.rp-online.de/duesseldorf/81405_schickimicki-duesseldorf");
        assertEquals(eventModel.locationModel.descriptionTeaser, "dt");
        assertEquals(eventModel.locationModel.link, "www.facebook.com/schickimicki");
        assertEquals(eventModel.locationModel.tel, "0211-135034");
        assertEquals(eventModel.locationModel.fax, "");
        assertEquals(eventModel.locationModel.street, "Neustraße 51");
        assertEquals(eventModel.locationModel.postcode, "40213");
        assertEquals(eventModel.locationModel.latitude, 51.2258268d, 1e-15);
        assertEquals(eventModel.locationModel.longitude, 6.7760529d, 1e-15);


    }


    @Test
    public void eventModelCityTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;

        assertEquals(eventModel.locationModel.cityModel.id, 104);
        assertEquals(eventModel.locationModel.cityModel.url, "duesseldorf");
        assertEquals(eventModel.locationModel.cityModel.name, "Düsseldorf");


    }

    @Test
    public void eventModelTypeTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;

        assertEquals(eventModel.typeModels.get(0).id, 41);
        assertEquals(eventModel.typeModels.get(0).name, "Konzerte & Nachtleben");
        assertEquals(eventModel.typeModels.get(1).id, 59);
        assertEquals(eventModel.typeModels.get(1).name, "90s");
        assertEquals(eventModel.typeModels.get(2).id, 53);
        assertEquals(eventModel.typeModels.get(2).name, "Rock");
        assertEquals(eventModel.typeModels.get(3).id, 52);
        assertEquals(eventModel.typeModels.get(3).name, "RnB & HipHop");

    }

    @Test
    public void eventModelEventUserTest() throws Exception {
        EventResponse eventResponse = new Gson().fromJson(eventJsonMock, EventResponse.class);
        EventModel eventModel = eventResponse.eventModel;
        UserModel userModel = eventModel.userModel;

        assertEquals(userModel.id, 62521);
        assertEquals(userModel.email, "schickimickiclub@gmx.de");
        assertEquals(userModel.firstName, "Daniel");
        assertEquals(userModel.lastName, "Vollmer");
        assertEquals(userModel.displayUsername, "Schickimicki");
        assertEquals(userModel.countFriends, 0);
        assertEquals(userModel.countEventAbo, 0);
        assertEquals(userModel.countLocationAbo, 0);
        assertEquals(userModel.countTopicAbo, 0);

    }

    @Test
    public void eventModelUserTest() throws Exception {
        UserResponse userResponse = new Gson().fromJson(userJsonMock, UserResponse.class);
        UserModel userModel = userResponse.userModel;

        assertEquals(userModel.id, 1315);
        assertEquals(userModel.email, "gp@popula.de");
        assertEquals(userModel.nickname, "hg");
        assertEquals(userModel.firstName, "XTRA");
        assertEquals(userModel.lastName, "Redaktion");
        assertEquals(userModel.description, "hallo ich bin gregor");
        assertEquals(userModel.image, "http://s3-eu-west-1.amazonaws.com/popula/user/000001/gp-popula-de_1315.jpg");
        assertEquals(userModel.image300, "http://s3-eu-west-1.amazonaws.com/popula/user/000001/thumbnail/gp-popula-de_1315_300.jpg");
        assertEquals(userModel.image320, "http://s3-eu-west-1.amazonaws.com/popula/user/000001/thumbnail/gp-popula-de_1315_320.jpg");
        assertEquals(userModel.displayUsername, "hg");
        assertEquals(userModel.countFriends, 1);
        assertEquals(userModel.countEventAbo, 3);
        assertEquals(userModel.countLocationAbo, 0);
        assertEquals(userModel.countTopicAbo, 0);


    }

}