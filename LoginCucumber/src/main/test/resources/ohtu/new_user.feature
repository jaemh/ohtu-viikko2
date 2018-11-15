Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  username "laura" and password "lauralaura1" are entered
        Then  system will respond with "new user registered"

  
