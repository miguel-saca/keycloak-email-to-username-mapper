<?php

$config = array(

    'admin' => array(
        'core:AdminPassword',
    ),

    'example-userpass' => array(
        'exampleauth:UserPass',
        'miguel.saca:mypassword' => array(
            'uid' => array('1'),
            'eduPersonAffiliation' => array('group1'),
            'mail' => 'miguel.saca@mail.com',
            'givenName' => 'miguel',
            'sn' => 'saca'
        ),
        'firtsname.lastname:mypassword' => array(
            'uid' => array('2'),
            'eduPersonAffiliation' => array('group2'),
            'mail' => 'firtsname.lastname@mail.com',
            'givenName' => 'firtsname',
            'sn' => 'lastname'
        ),
    ),

);