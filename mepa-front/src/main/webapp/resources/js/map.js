var map = null;
var infowindow = null;
var content = null;
var center = new google.maps.LatLng(48.8532, 2.3499); //Centre de paris (Notre-Dame de Paris)
var pointsToDisplay = [];
var markers = [];
var markersFiltered = [];
var datasetId = null;
var fieldValues = null;
var isCarto = false;
var markerClusterer = null;

var Arr1 = [
    new google.maps.LatLng(48.85952,2.334852),
    new google.maps.LatLng(48.85376,2.344723),
    new google.maps.LatLng(48.86336,2.350903),
    new google.maps.LatLng(48.868328,2.330303),
    new google.maps.LatLng(48.870078,2.328157),
    new google.maps.LatLng(48.86962700000001,2.3254970000000004),
    new google.maps.LatLng(48.863585,2.321033),
    new google.maps.LatLng(48.85952,2.334852)
];
var Arr2 = [
    new google.maps.LatLng(48.868328,2.3302169999999998),
    new google.maps.LatLng(48.86333100000001,2.3509030000000006),
    new google.maps.LatLng(48.869401,2.3543359999999995),
    new google.maps.LatLng(48.872054,2.3400879999999997),
    new google.maps.LatLng(48.870078,2.328157),
    new google.maps.LatLng(48.868328,2.3302169999999998)
];
var Arr3 = [
    new google.maps.LatLng(48.862117,2.350044),
    new google.maps.LatLng(48.860141,2.356653),
    new google.maps.LatLng(48.858447000000005,2.358971),
    new google.maps.LatLng(48.857261,2.361546),
    new google.maps.LatLng(48.856358,2.364378),
    new google.maps.LatLng(48.855793,2.368412),
    new google.maps.LatLng(48.86319000000001,2.366695),
    new google.maps.LatLng(48.866183,2.3649790000000004),
    new google.maps.LatLng(48.867764,2.362576),
    new google.maps.LatLng(48.869373,2.3542500000000004),
    new google.maps.LatLng(48.862117,2.350044)
];
var Arr4 = [
    new google.maps.LatLng(48.85378800000001,2.344723),
    new google.maps.LatLng(48.849072,2.3612879999999996),
    new google.maps.LatLng(48.84653,2.365065),
    new google.maps.LatLng(48.847208,2.3663089999999998),
    new google.maps.LatLng(48.85263,2.369013),
    new google.maps.LatLng(48.855906,2.368326),
    new google.maps.LatLng(48.856301,2.364635),
    new google.maps.LatLng(48.857261,2.3616309999999996),
    new google.maps.LatLng(48.858447000000005,2.358971),
    new google.maps.LatLng(48.860198,2.356567),
    new google.maps.LatLng(48.86206099999999,2.350044),
    new google.maps.LatLng(48.85378800000001,2.344723)
];
var Arr5 = [
    new google.maps.LatLng(48.853873,2.344637),
    new google.maps.LatLng(48.839695,2.3365689999999995),
    new google.maps.LatLng(48.83743599999999,2.346954),
    new google.maps.LatLng(48.836984,2.351847),
    new google.maps.LatLng(48.839977999999995,2.3617169999999996),
    new google.maps.LatLng(48.844102,2.3646350000000003),
    new google.maps.LatLng(48.849467,2.3566529999999997),
    new google.maps.LatLng(48.853873,2.344637)

];
var Arr6 = [
    new google.maps.LatLng(48.853873,2.344637),
    new google.maps.LatLng(48.857826,2.337685),
    new google.maps.LatLng(48.857882,2.3352809999999997),
    new google.maps.LatLng(48.858503,2.3332209999999995),
    new google.maps.LatLng(48.852065,2.3287580000000005),
    new google.maps.LatLng(48.851557,2.326956),
    new google.maps.LatLng(48.848055,2.319489),
    new google.maps.LatLng(48.846982,2.316828),
    new google.maps.LatLng(48.845569999999995,2.3189739999999994),
    new google.maps.LatLng(48.839808,2.336569),
    new google.maps.LatLng(48.853873,2.344637)
];
var Arr7 = [
    new google.maps.LatLng(48.858616,2.333221),
    new google.maps.LatLng(48.862569,2.318115),
    new google.maps.LatLng(48.86251299999999,2.3089309999999994),
    new google.maps.LatLng(48.86189199999999,2.299662),
    new google.maps.LatLng(48.860932,2.296829),
    new google.maps.LatLng(48.85720499999999,2.2913360000000003),
    new google.maps.LatLng(48.845175,2.310562),
    new google.maps.LatLng(48.847095,2.316999),
    new google.maps.LatLng(48.849298000000005,2.3222350000000005),
    new google.maps.LatLng(48.851670000000006,2.3272130000000004),
    new google.maps.LatLng(48.852122,2.3287580000000005),
    new google.maps.LatLng(48.858616,2.333221)
];
var Arr8 = [
    new google.maps.LatLng(48.869514,2.325411),
    new google.maps.LatLng(48.869796,2.3258400000000004),
    new google.maps.LatLng(48.872393,2.326441),
    new google.maps.LatLng(48.873522,2.326956),
    new google.maps.LatLng(48.875385,2.3266979999999995),
    new google.maps.LatLng(48.88351300000001,2.3272130000000004),
    new google.maps.LatLng(48.88148100000001,2.3167420000000005),
    new google.maps.LatLng(48.880578,2.3092749999999995),
    new google.maps.LatLng(48.87832,2.298288),
    new google.maps.LatLng(48.873974,2.295027),
    new google.maps.LatLng(48.871377,2.297173),
    new google.maps.LatLng(48.86962700000001,2.298374),
    new google.maps.LatLng(48.865449000000005,2.299747),
    new google.maps.LatLng(48.864262999999994,2.302237),
    new google.maps.LatLng(48.864489,2.3182009999999997),
    new google.maps.LatLng(48.863585,2.321033),
    new google.maps.LatLng(48.869514,2.325411)
];
var Arr9 = [
    new google.maps.LatLng(48.875667,2.326698),
    new google.maps.LatLng(48.873579,2.32687),
    new google.maps.LatLng(48.872506,2.326441),
    new google.maps.LatLng(48.86974000000001,2.3258400000000004),
    new google.maps.LatLng(48.872054,2.3400879999999997),
    new google.maps.LatLng(48.87064300000001,2.347898),
    new google.maps.LatLng(48.873861,2.347898),
    new google.maps.LatLng(48.875554,2.34807),
    new google.maps.LatLng(48.876909,2.348671),
    new google.maps.LatLng(48.877474,2.3491),
    new google.maps.LatLng(48.879167,2.349186),
    new google.maps.LatLng(48.879788,2.349701),
    new google.maps.LatLng(48.881764,2.349958),
    new google.maps.LatLng(48.88373899999999,2.3496149999999996),
    new google.maps.LatLng(48.882046,2.339401),
    new google.maps.LatLng(48.884755,2.329617),
    new google.maps.LatLng(48.88351300000001,2.3272130000000004),
    new google.maps.LatLng(48.875667,2.326698)
];
var Arr10 = [
    new google.maps.LatLng(48.88018,2.3497800000000004),
    new google.maps.LatLng(48.879220000000004,2.3491),
    new google.maps.LatLng(48.87753000000001,2.3491),
    new google.maps.LatLng(48.87555,2.34798),
    new google.maps.LatLng(48.870639999999995,2.34798),
    new google.maps.LatLng(48.86787,2.3624),
    new google.maps.LatLng(48.86691,2.36386),
    new google.maps.LatLng(48.86804,2.36506),
    new google.maps.LatLng(48.871990000000004,2.3769),
    new google.maps.LatLng(48.87803,2.3703),
    new google.maps.LatLng(48.882549999999995,2.37004),
    new google.maps.LatLng(48.88407,2.36867),
    new google.maps.LatLng(48.884529,2.36043),
    new google.maps.LatLng(48.88373000000001,2.3496100000000006),
    new google.maps.LatLng(48.88018,2.3497800000000004)
];
var Arr11 = [
    new google.maps.LatLng(48.86804600000001,2.3649790000000004),
    new google.maps.LatLng(48.866973,2.363691),
    new google.maps.LatLng(48.866239,2.3648929999999995),
    new google.maps.LatLng(48.863416,2.36661),
    new google.maps.LatLng(48.853986,2.36867),
    new google.maps.LatLng(48.852461,2.3714159999999995),
    new google.maps.LatLng(48.850597,2.3792270000000006),
    new google.maps.LatLng(48.850145,2.384377),
    new google.maps.LatLng(48.848168,2.399225),
    new google.maps.LatLng(48.851218,2.398453),
    new google.maps.LatLng(48.856471,2.3944189999999996),
    new google.maps.LatLng(48.858165,2.389956),
    new google.maps.LatLng(48.862795,2.387552),
    new google.maps.LatLng(48.866522,2.3835180000000005),
    new google.maps.LatLng(48.872054,2.376738),
    new google.maps.LatLng(48.86804600000001,2.3649790000000004)

];
var Arr12 = [
    new google.maps.LatLng(48.84664300000001,2.3649790000000004),
    new google.maps.LatLng(48.827661,2.3898700000000006),
    new google.maps.LatLng(48.82834,2.39459),
    new google.maps.LatLng(48.82947,2.399654),
    new google.maps.LatLng(48.831165,2.4024010000000002),
    new google.maps.LatLng(48.83297300000001,2.405577),
    new google.maps.LatLng(48.835232999999995,2.4106409999999996),
    new google.maps.LatLng(48.837831,2.412271),
    new google.maps.LatLng(48.841108,2.4128719999999997),
    new google.maps.LatLng(48.846869000000005,2.4136450000000003),
    new google.maps.LatLng(48.848225,2.3989679999999995),
    new google.maps.LatLng(48.850258,2.3836899999999996),
    new google.maps.LatLng(48.850597,2.379913),
    new google.maps.LatLng(48.85240400000001,2.3715019999999996),
    new google.maps.LatLng(48.853873,2.368755),
    new google.maps.LatLng(48.8528,2.369013),
    new google.maps.LatLng(48.847434,2.366524),
    new google.maps.LatLng(48.84664300000001,2.3649790000000004)
];
var Arr13 = [
    new google.maps.LatLng(48.83850900000001,2.341976),
    new google.maps.LatLng(48.831956000000005,2.3411180000000003),
    new google.maps.LatLng(48.826644,2.341461),
    new google.maps.LatLng(48.822689000000004,2.341461),
    new google.maps.LatLng(48.81975,2.3443790000000004),
    new google.maps.LatLng(48.816868,2.3443790000000004),
    new google.maps.LatLng(48.816811,2.346954),
    new google.maps.LatLng(48.818507,2.3515890000000006),
    new google.maps.LatLng(48.818055,2.3542500000000004),
    new google.maps.LatLng(48.816755,2.356911),
    new google.maps.LatLng(48.816359,2.362576),
    new google.maps.LatLng(48.818959,2.369528),
    new google.maps.LatLng(48.821898,2.3781970000000006),
    new google.maps.LatLng(48.826305,2.386436),
    new google.maps.LatLng(48.836814,2.373734),
    new google.maps.LatLng(48.840543,2.369099),
    new google.maps.LatLng(48.844158,2.3642920000000003),
    new google.maps.LatLng(48.840091,2.361546),
    new google.maps.LatLng(48.83704,2.3519330000000003),
    new google.maps.LatLng(48.837605,2.346783),
    new google.maps.LatLng(48.83850900000001,2.341976)
];
var Arr14 = [
    new google.maps.LatLng(48.819863,2.344379),
    new google.maps.LatLng(48.823028,2.3414609999999993),
    new google.maps.LatLng(48.831956000000005,2.3411180000000003),
    new google.maps.LatLng(48.838622,2.341805),
    new google.maps.LatLng(48.839752,2.3368260000000003),
    new google.maps.LatLng(48.843706,2.324982),
    new google.maps.LatLng(48.841108,2.3227500000000005),
    new google.maps.LatLng(48.84167299999999,2.320347),
    new google.maps.LatLng(48.825627,2.3019790000000007),
    new google.maps.LatLng(48.820767000000004,2.3251530000000002),
    new google.maps.LatLng(48.819072,2.332878),
    new google.maps.LatLng(48.818055,2.334595),
    new google.maps.LatLng(48.817377,2.3363109999999994),
    new google.maps.LatLng(48.817377,2.3382),
    new google.maps.LatLng(48.817037,2.344551),
    new google.maps.LatLng(48.819863,2.344379)
];
var Arr15 = [
    new google.maps.LatLng(48.857148,2.2913359999999994),
    new google.maps.LatLng(48.856358,2.2899630000000006),
    new google.maps.LatLng(48.855115,2.2892759999999996),
    new google.maps.LatLng(48.847434,2.279835),
    new google.maps.LatLng(48.844836,2.2765730000000004),
    new google.maps.LatLng(48.83568400000001,2.2678180000000006),
    new google.maps.LatLng(48.83591,2.2709080000000004),
    new google.maps.LatLng(48.835571,2.2739979999999997),
    new google.maps.LatLng(48.83342499999999,2.280006),
    new google.maps.LatLng(48.828227,2.291851),
    new google.maps.LatLng(48.825966,2.3021510000000003),
    new google.maps.LatLng(48.84156,2.320518),
    new google.maps.LatLng(48.841108,2.3227500000000005),
    new google.maps.LatLng(48.84381900000001,2.3248100000000003),
    new google.maps.LatLng(48.845514,2.318974),
    new google.maps.LatLng(48.847095,2.316742),
    new google.maps.LatLng(48.845400999999995,2.31039),
    new google.maps.LatLng(48.857148,2.2913359999999994)
];
var Arr16 = [
    new google.maps.LatLng(48.874087,2.295113),
    new google.maps.LatLng(48.878264,2.281208),
    new google.maps.LatLng(48.87634500000001,2.2794910000000006),
    new google.maps.LatLng(48.874313,2.2767450000000005),
    new google.maps.LatLng(48.870022,2.272797),
    new google.maps.LatLng(48.868215000000006,2.270737),
    new google.maps.LatLng(48.864941,2.2683330000000006),
    new google.maps.LatLng(48.862682,2.265759),
    new google.maps.LatLng(48.859859,2.263699),
    new google.maps.LatLng(48.852856,2.252884),
    new google.maps.LatLng(48.851049,2.252712),
    new google.maps.LatLng(48.849016,2.2533990000000004),
    new google.maps.LatLng(48.847208,2.2551160000000006),
    new google.maps.LatLng(48.845288,2.255802),
    new google.maps.LatLng(48.842689,2.254686),
    new google.maps.LatLng(48.839752,2.254601),
    new google.maps.LatLng(48.837266,2.255802),
    new google.maps.LatLng(48.836193,2.257862),
    new google.maps.LatLng(48.835571,2.260523),
    new google.maps.LatLng(48.835401999999995,2.2613809999999996),
    new google.maps.LatLng(48.83591,2.262154),
    new google.maps.LatLng(48.84156,2.2678180000000006),
    new google.maps.LatLng(48.850315,2.275801),
    new google.maps.LatLng(48.854663,2.283182),
    new google.maps.LatLng(48.860085,2.289963),
    new google.maps.LatLng(48.863247,2.294941),
    new google.maps.LatLng(48.864489,2.3018070000000006),
    new google.maps.LatLng(48.865788,2.299662),
    new google.maps.LatLng(48.869795999999994,2.2983739999999995),
    new google.maps.LatLng(48.874087,2.295113)
];
var Arr17 = [
    new google.maps.LatLng(48.878264,2.298374),
    new google.maps.LatLng(48.880522000000006,2.308674),
    new google.maps.LatLng(48.881312,2.316055),
    new google.maps.LatLng(48.883457,2.3272130000000004),
    new google.maps.LatLng(48.887859,2.3256679999999994),
    new google.maps.LatLng(48.900161,2.329788),
    new google.maps.LatLng(48.900048,2.3217200000000005),
    new google.maps.LatLng(48.894744,2.3066139999999997),
    new google.maps.LatLng(48.889101,2.297001),
    new google.maps.LatLng(48.88774600000001,2.291679),
    new google.maps.LatLng(48.88278,2.2839550000000006),
    new google.maps.LatLng(48.878264,2.281208),
    new google.maps.LatLng(48.874087,2.295284),
    new google.maps.LatLng(48.878264,2.298374)
];
var Arr18 = [
    new google.maps.LatLng(48.884247,2.364979),
    new google.maps.LatLng(48.88673,2.3665240000000005),
    new google.maps.LatLng(48.894067,2.369785),
    new google.maps.LatLng(48.895534,2.3718450000000004),
    new google.maps.LatLng(48.896662,2.370129),
    new google.maps.LatLng(48.900161,2.3703),
    new google.maps.LatLng(48.900499,2.3545069999999995),
    new google.maps.LatLng(48.900161,2.3296169999999994),
    new google.maps.LatLng(48.88785899999999,2.325497),
    new google.maps.LatLng(48.88357,2.3272130000000004),
    new google.maps.LatLng(48.884699,2.3296169999999994),
    new google.maps.LatLng(48.882102,2.339573),
    new google.maps.LatLng(48.883796,2.349701),
    new google.maps.LatLng(48.884473,2.36043),
    new google.maps.LatLng(48.884247,2.364979)
];
var Arr19 = [
    new google.maps.LatLng(48.872054,2.3766519999999995),
    new google.maps.LatLng(48.875442,2.390728),
    new google.maps.LatLng(48.875329,2.396049),
    new google.maps.LatLng(48.876119,2.4025730000000003),
    new google.maps.LatLng(48.877812,2.4082370000000006),
    new google.maps.LatLng(48.879619,2.407036),
    new google.maps.LatLng(48.88097299999999,2.4032589999999994),
    new google.maps.LatLng(48.883683000000005,2.398796),
    new google.maps.LatLng(48.885715000000005,2.3969080000000003),
    new google.maps.LatLng(48.898807000000005,2.3926159999999994),
    new google.maps.LatLng(48.90004799999999,2.3912429999999993),
    new google.maps.LatLng(48.900499,2.3872950000000004),
    new google.maps.LatLng(48.90004799999999,2.3766519999999995),
    new google.maps.LatLng(48.900274,2.370129),
    new google.maps.LatLng(48.89666199999999,2.3699569999999994),
    new google.maps.LatLng(48.895534,2.371502),
    new google.maps.LatLng(48.894293,2.3697849999999994),
    new google.maps.LatLng(48.886956,2.366695),
    new google.maps.LatLng(48.88436,2.3649790000000004),
    new google.maps.LatLng(48.884134,2.368412),
    new google.maps.LatLng(48.88266699999999,2.369957),
    new google.maps.LatLng(48.877925000000005,2.3703),
    new google.maps.LatLng(48.872054,2.3766519999999995)
];
var Arr20 = [
    new google.maps.LatLng(48.863247,2.387037),
    new google.maps.LatLng(48.85810800000001,2.3900410000000005),
    new google.maps.LatLng(48.85669600000001,2.3941609999999995),
    new google.maps.LatLng(48.85138800000001,2.398453),
    new google.maps.LatLng(48.848168,2.3991389999999995),
    new google.maps.LatLng(48.846926,2.413902),
    new google.maps.LatLng(48.851162,2.414589),
    new google.maps.LatLng(48.854663,2.413044),
    new google.maps.LatLng(48.858616,2.413645),
    new google.maps.LatLng(48.860819,2.4132160000000002),
    new google.maps.LatLng(48.864037,2.412701),
    new google.maps.LatLng(48.872054000000006,2.412357),
    new google.maps.LatLng(48.877812,2.408066),
    new google.maps.LatLng(48.87623200000001,2.4025730000000003),
    new google.maps.LatLng(48.875442,2.396736),
    new google.maps.LatLng(48.875442,2.390728),
    new google.maps.LatLng(48.872054,2.3768229999999995),
    new google.maps.LatLng(48.863247,2.387037)
];
var ArrSeine = [
    new google.maps.LatLng(48.829696,2.2618099999999997),
    new google.maps.LatLng(48.845966,2.277775),
    new google.maps.LatLng(48.857487,2.291851),
    new google.maps.LatLng(48.861779,2.299232),
    new google.maps.LatLng(48.862343,2.309017),
    new google.maps.LatLng(48.86245600000001,2.3186300000000006),
    new google.maps.LatLng(48.857487,2.336998),
    new google.maps.LatLng(48.85376,2.3455810000000006),
    new google.maps.LatLng(48.84867700000001,2.3582840000000003),
    new google.maps.LatLng(48.818733,2.3963930000000007),
    new google.maps.LatLng(48.82088,2.399311),
    new google.maps.LatLng(48.850032,2.360687),
    new google.maps.LatLng(48.85568,2.3491860000000004),
    new google.maps.LatLng(48.859633,2.338028),
    new google.maps.LatLng(48.863924,2.3206900000000004),
    new google.maps.LatLng(48.864715,2.317257),
    new google.maps.LatLng(48.864262999999994,2.3007770000000005),
    new google.maps.LatLng(48.862908,2.2942540000000005),
    new google.maps.LatLng(48.850032,2.2760580000000004),
    new google.maps.LatLng(48.831617,2.258034),
    new google.maps.LatLng(48.829696,2.2618099999999997)
];

var Arr1Poly = new google.maps.Polygon({
    paths: Arr1
});

var Arr2Poly = new google.maps.Polygon({
    paths: Arr2
});

var Arr3Poly = new google.maps.Polygon({
    paths: Arr3
});

var Arr4Poly = new google.maps.Polygon({
    paths: Arr4
});

var Arr5Poly = new google.maps.Polygon({
    paths: Arr5
});

var Arr6Poly = new google.maps.Polygon({
    paths: Arr6
});

var Arr7Poly = new google.maps.Polygon({
    paths: Arr7
});

var Arr8Poly = new google.maps.Polygon({
    paths: Arr8
});

var Arr9Poly = new google.maps.Polygon({
    paths: Arr9
});

var Arr10Poly = new google.maps.Polygon({
    paths: Arr10
});

var Arr11Poly = new google.maps.Polygon({
    paths: Arr11
});

var Arr12Poly = new google.maps.Polygon({
    paths: Arr12
});

var Arr13Poly = new google.maps.Polygon({
    paths: Arr13
});

var Arr14Poly = new google.maps.Polygon({
    paths: Arr14
});

var Arr15Poly = new google.maps.Polygon({
    paths: Arr15
});

var Arr16Poly = new google.maps.Polygon({
    paths: Arr16
});

var Arr17Poly = new google.maps.Polygon({
    paths: Arr17
});

var Arr18Poly = new google.maps.Polygon({
    paths: Arr18
});

var Arr19Poly = new google.maps.Polygon({
    paths: Arr19
});

var Arr20Poly = new google.maps.Polygon({
    paths: Arr20
});

var ArrSeinePoly = new google.maps.Polygon({
    paths: ArrSeine
});

function initialize()
{
    $("#map-canvas").remove();
    $("#carto-div-right").append("<div id='map-canvas' class='map-canvas'></div>");

    datasetId = document.getElementById("data").value;

    var mapOptions = {
        zoom: 12
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    infowindow = new google.maps.InfoWindow({
        maxWidth : 250
    });

    // HTML5 geolocation
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {

            var pos = new google.maps.LatLng(position.coords.latitude,
                position.coords.longitude);

            map.setCenter(pos);
            content = 'You are here !';

            pointsToDisplay.push({pos : pos, info : content, isGeoLoc : true});

            drawMultipleMarkersOnMap();

        }, function() {
            handleNoGeolocation(true);
            drawMultipleMarkersOnMap();
        });
    } else {
        // Browser doesn't support Geolocation
        handleNoGeolocation(false);
        drawMultipleMarkersOnMap();
    }
}

function handleNoGeolocation(errorFlag) {
    if (errorFlag)
        content = 'Error: The Geolocation service failed.';
    else
        content = 'Error: Your browser doesn\'t support geolocation.';

    map.setCenter(center);
    pointsToDisplay.push({pos : center, info : content, isGeoLoc : true});
}

function drawMultipleMarkersOnMap() {

    var dataJson = null;

    $.ajax({
        dataType: "json",
        url : "/mepa-front/api/dataSet/" + datasetId + ".json",
        success : function(data)
        {
            fieldValues = data.fieldMap;
            isCarto = data.isCarto;
        },
        async : false});

    if (isCarto && fieldValues.hasOwnProperty('latitude') && fieldValues.hasOwnProperty('longitude'))
    {
        $.ajax({
            dataType: "json",
            url: "/mepa-front/api/dataSet/" + datasetId + "/data.json",
            success: function (data) {
                dataJson = data;
            },
            async: false
        });

        for (var i = 0; i < dataJson.data.latitude.value.length - 1;  i++)
        {
            var info = "";

            for (var key in fieldValues)
            {
                if (dataJson.data.hasOwnProperty(key))
                    info += "<b>" + key + "</b>" + " : " + dataJson.data[key].value[i] + "<br>";
            }

            pointsToDisplay.push({
                pos: new google.maps.LatLng(parseFloat(dataJson.data.latitude.value[i]),
                    parseFloat(dataJson.data.longitude.value[i])), info: info, isGeoLoc: false
            });
        }
    }

    for (var j = 0; j < pointsToDisplay.length; j++) {
        addMarker(pointsToDisplay[j].pos, pointsToDisplay[j].info, pointsToDisplay[j].isGeoLoc);
    }

    markerClusterer = new MarkerClusterer(map, markers);

    google.maps.event.addListener(map, 'click', function() {
        infowindow.close(map, this);
    });
}


function addMarker(position, content, isGeoLoc) {

    var marker = null;

    if (isGeoLoc == false)
    {
        marker = new google.maps.Marker({
            position: position,
            map: map
        });
    }
    else
    {
        marker = new google.maps.Marker({
            position: position,
            map: map,
            icon: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
        });
    }
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.setContent(content);
        infowindow.open(map, this);
    });

    markers.push(marker);
}


// Sets the map on all markers in the array.
function setAllMap(map)
{
    for (var i = 0; i < markers.length; i++)
        markers[i].setMap(map);
}

// Sets the map on some markers in the array.
function setSomeMap(map, polyFilter)
{
    markersFiltered = [];
    for (var i = 0; i < markers.length; i++)
    {
        if (!google.maps.geometry.poly.containsLocation(markers[i].position, polyFilter))
            markers[i].setMap(map);

        else
            markersFiltered.push(markers[i]);
    }
}

// Removes the markers from the map, but keeps them in the array.
function clearAllMarkers()
{
    setAllMap(null);
}

// Removes markers in markersToHide from the map, but keeps them in the array.
function clearSomeMarkers(polyFilter)
{
    setSomeMap(null, polyFilter);
}

// Shows any markers currently in the array.
function showMarkers()
{
    setAllMap(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers()
{
    clearAllMarkers();
    markers = [];
}


function isCartTabActive()
{
    if ($('#carto-tab').attr('class') == 'active')
        initialize();

    else
        setTimeout(isCartTabActive, 500);
}

google.maps.event.addDomListener(window, 'load', isCartTabActive);

$("#tous").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    markerClusterer = new MarkerClusterer(map, markers);
});

$("#1er").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr1Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#2e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr2Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#3e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr3Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#4e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr4Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#5e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr5Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#6e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr6Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#7e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr7Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#8e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr8Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#9e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr9Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#10e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr10Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#11e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr11Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#12e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr12Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#13e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr13Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#14e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr14Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#15e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr15Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#16e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr16Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#17e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr17Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#18e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr18Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#19e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr19Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#20e").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(Arr20Poly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});

$("#seine").click(function() {
    markerClusterer.clearMarkers();
    showMarkers();
    clearSomeMarkers(ArrSeinePoly);
    markerClusterer = new MarkerClusterer(map, markersFiltered);
});