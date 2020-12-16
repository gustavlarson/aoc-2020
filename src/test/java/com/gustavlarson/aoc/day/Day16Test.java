package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {

    public static final String INPUT1 = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50
                            
            your ticket:
            7,1,14
                            
            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12""";
    public static final String INPUT2 = """
            departure location: 32-842 or 854-967
            departure station: 44-564 or 582-955
            departure platform: 35-904 or 915-970
            departure track: 45-117 or 133-961
            departure date: 50-697 or 714-969
            departure time: 48-205 or 221-973
            arrival location: 26-252 or 260-955
            arrival station: 45-337 or 356-963
            arrival platform: 45-507 or 520-961
            arrival track: 47-225 or 236-965
            class: 45-362 or 375-971
            duration: 30-264 or 281-959
            price: 50-397 or 414-960
            route: 40-460 or 473-958
            row: 49-634 or 650-957
            seat: 46-537 or 554-967
            train: 28-494 or 506-962
            type: 43-862 or 874-964
            wagon: 35-653 or 665-967
            zone: 36-200 or 206-969

            your ticket:
            139,67,71,59,149,89,101,83,107,103,79,157,151,113,61,109,73,97,137,53

            nearby tickets:
            892,600,250,783,477,902,794,938,819,560,755,264,607,520,737,312,324,92,307,639
            415,878,633,607,753,675,440,441,83,880,934,941,823,949,8,451,590,242,165,281
            754,328,624,175,284,765,854,459,302,926,859,607,587,574,522,595,115,798,737,895
            527,370,302,615,333,437,264,607,281,145,837,142,251,309,238,306,564,173,414,288
            177,194,417,134,717,714,560,385,783,439,60,535,523,838,932,659,189,787,448,251
            823,819,137,892,346,74,50,885,330,357,855,780,56,146,758,92,197,770,619,940
            877,379,114,858,609,221,688,179,207,77,245,733,835,97,940,443,763,391,432,159
            164,419,807,479,307,948,883,546,723,376,417,58,528,683,592,290,457,735,784,189
            653,556,586,171,256,904,557,291,939,324,860,862,937,554,903,823,917,454,311,717
            262,827,764,779,54,21,831,679,608,740,624,727,694,383,379,102,825,103,861,779
            524,197,298,829,623,855,734,54,285,691,889,840,554,99,841,707,53,795,300,305
            67,877,678,114,236,164,197,945,196,159,450,189,765,206,182,797,766,610,318,818
            617,196,107,106,310,385,285,102,892,745,901,808,838,668,939,921,814,84,547,260
            885,138,984,490,422,249,377,606,309,225,605,251,84,794,135,301,808,480,631,481
            64,723,101,860,309,689,594,759,244,911,632,304,303,55,63,140,163,361,152,430
            242,741,138,314,784,447,691,982,723,917,328,780,299,926,88,678,173,459,584,485
            881,490,300,854,412,187,623,333,743,630,838,760,779,523,145,329,762,753,380,79
            157,200,160,102,433,310,256,629,332,672,239,601,359,241,481,555,742,384,375,378
            574,244,145,792,486,734,806,84,826,780,763,742,596,103,138,623,887,823,388,82
            454,817,321,435,810,358,740,815,249,838,252,509,650,860,858,195,582,146,249,614
            61,245,300,309,634,748,427,593,796,438,377,155,652,466,281,151,880,632,731,310
            935,318,111,200,193,796,938,930,537,792,618,531,676,661,260,530,236,791,142,764
            822,264,597,631,564,98,84,818,157,433,341,722,924,584,783,459,441,167,479,558
            836,286,650,449,170,837,296,109,160,173,521,325,425,901,139,921,436,358,468,75
            70,199,181,453,243,148,691,469,242,53,788,737,927,903,377,794,745,488,715,74
            718,928,715,57,133,100,728,592,489,8,919,98,375,297,721,184,332,306,157,422
            803,169,733,293,793,55,913,221,431,112,94,138,682,598,688,714,671,331,239,594
            160,175,806,742,542,858,772,815,761,932,442,758,100,831,562,479,899,483,532,104
            180,63,586,683,417,924,944,758,928,829,310,610,657,117,453,891,301,89,263,755
            781,18,943,426,181,691,734,789,436,457,739,942,389,759,631,895,621,243,739,820
            732,249,317,875,900,860,562,491,459,727,859,891,480,696,89,718,248,351,834,812
            238,820,932,475,821,453,833,827,149,801,917,930,197,263,67,577,262,529,160,307
            237,807,284,948,665,225,802,341,108,683,606,740,383,493,244,309,946,134,762,766
            52,64,773,562,675,666,821,313,391,509,61,694,917,53,420,315,90,921,51,437
            804,776,24,185,456,821,288,622,860,892,920,830,744,631,561,792,949,315,245,616
            810,390,615,722,859,737,606,924,468,69,303,197,605,89,456,333,167,289,879,879
            477,102,817,494,115,134,794,101,894,522,942,779,459,723,719,524,308,700,617,833
            115,586,728,536,187,99,875,685,507,792,358,151,145,378,454,467,294,109,308,776
            520,430,630,604,840,939,617,168,167,240,332,188,261,564,987,379,320,735,522,53
            788,895,93,303,443,803,95,285,459,318,692,944,369,149,524,599,716,759,159,179
            306,293,685,732,820,764,410,356,78,168,695,246,93,285,71,148,932,554,199,147
            111,187,92,424,617,622,487,751,881,985,506,152,262,389,804,676,322,60,317,183
            538,450,62,252,813,797,535,624,939,178,437,758,88,310,651,802,452,250,534,150
            984,318,806,696,433,475,422,769,928,137,842,858,727,557,176,560,524,116,584,327
            933,335,877,775,729,725,80,390,751,78,933,170,56,254,860,443,382,780,854,112
            312,918,156,416,806,517,415,475,797,650,792,818,829,813,856,774,397,563,728,423
            803,727,770,561,674,22,311,564,64,305,616,759,72,786,601,666,86,147,946,820
            314,564,934,197,135,837,135,899,61,682,334,460,676,889,521,396,395,212,598,776
            206,237,563,677,474,533,308,798,780,759,764,112,692,416,486,76,595,940,97,523
            292,450,743,741,521,65,940,605,337,823,822,350,945,770,452,676,823,690,494,476
            144,150,820,174,918,840,61,798,143,160,665,625,464,243,479,97,882,449,776,321
            558,785,536,324,431,583,744,732,302,391,301,359,490,892,172,362,836,150,513,396
            412,523,726,675,115,786,885,925,633,688,424,877,308,794,785,583,429,319,694,882
            176,775,614,666,461,586,882,65,769,805,488,926,937,289,455,596,485,784,330,733
            897,597,813,144,294,224,735,149,940,314,733,78,58,824,721,207,249,456,831,777
            674,834,426,251,620,817,383,630,881,176,449,139,787,811,97,722,87,461,85,360
            392,160,782,358,810,190,709,134,889,561,823,159,376,828,113,627,633,325,811,262
            838,697,597,841,745,899,915,190,948,827,672,627,239,673,767,917,434,24,449,440
            242,652,312,617,138,900,750,252,625,949,608,685,250,327,788,458,521,374,736,830
            897,588,715,988,200,781,328,361,628,337,262,318,103,193,178,224,306,673,774,486
            261,191,674,826,828,742,419,170,430,341,379,901,236,694,153,301,393,322,884,329
            435,298,318,797,751,557,442,883,422,252,658,176,883,767,115,332,898,819,630,940
            288,491,333,860,655,442,752,718,886,387,916,941,438,296,422,71,535,61,738,837
            824,819,599,334,815,238,816,645,68,238,621,816,457,240,858,427,58,295,735,362
            305,242,155,762,78,314,806,531,942,375,782,440,612,165,477,739,254,523,133,758
            75,297,319,105,86,410,747,171,528,757,885,741,281,386,667,935,680,289,919,104
            899,935,609,392,764,941,595,74,613,58,417,798,254,310,172,650,94,164,827,763
            383,378,222,182,433,460,894,802,888,873,727,772,172,886,812,809,481,603,242,933
            880,134,182,50,752,588,774,680,921,599,225,615,166,844,835,791,726,190,614,117
            195,494,942,183,330,604,476,908,175,141,917,91,718,899,601,929,69,806,945,90
            889,453,772,425,331,176,460,252,739,614,286,936,889,726,60,397,890,832,186,472
            938,481,921,595,687,432,848,189,61,736,306,882,262,557,750,161,438,531,613,931
            724,362,828,68,181,287,739,918,806,334,683,61,810,802,141,668,164,441,248,408
            171,826,534,443,80,425,719,884,285,473,665,23,302,284,195,805,632,819,794,601
            802,800,658,433,717,771,96,883,91,144,613,778,935,589,526,697,949,51,854,337
            56,723,299,389,784,536,826,532,50,521,773,920,583,489,925,876,792,550,148,316
            770,525,433,92,86,784,88,380,732,247,96,998,459,762,752,833,60,734,756,530
            321,224,66,157,327,522,21,595,617,452,675,99,813,555,607,520,82,487,605,821
            199,305,95,616,149,731,321,782,297,653,66,336,632,500,671,721,920,724,838,670
            758,335,740,687,414,225,482,618,190,645,138,768,251,331,944,782,742,486,682,308
            422,95,492,108,187,561,755,282,305,915,771,254,786,167,677,430,57,691,920,669
            494,820,730,932,197,897,687,925,92,110,314,561,442,386,719,903,861,683,841,371
            659,899,434,62,667,109,249,238,799,361,88,524,288,614,948,109,485,329,729,426
            295,747,650,383,858,935,806,616,259,446,380,379,99,779,94,287,283,765,139,64
            748,50,298,84,776,20,723,199,760,237,482,54,193,445,939,534,680,58,930,599
            511,80,95,162,666,582,530,525,432,671,919,286,150,448,562,771,807,148,802,249
            604,685,89,101,777,491,902,396,685,679,98,626,313,489,760,299,537,591,824,513
            537,175,918,89,486,431,899,620,402,138,483,756,832,769,761,70,384,534,361,330
            734,327,588,889,155,616,839,191,687,592,388,622,918,199,486,22,294,686,486,626
            635,523,144,779,182,697,562,554,379,453,289,381,679,943,438,650,168,805,74,594
            170,310,456,144,596,931,772,668,50,542,668,264,554,316,806,945,75,558,532,356
            799,557,252,777,746,885,757,324,929,59,729,454,116,795,418,161,662,653,717,141
            804,109,103,492,486,195,72,146,439,887,778,56,80,320,999,141,650,311,601,803
            719,61,115,459,100,433,336,522,738,681,256,102,617,395,904,318,535,167,888,807
            113,730,855,485,925,722,187,722,79,367,528,821,729,744,858,738,877,148,424,941
            862,670,51,437,823,532,855,56,134,424,674,187,157,689,108,941,370,737,439,221
            916,66,829,735,206,816,313,535,336,899,768,299,652,175,897,314,285,679,178,182
            104,95,434,246,188,822,423,599,701,283,818,778,802,261,102,715,780,615,290,750
            503,673,483,802,59,665,388,68,776,148,190,731,720,809,535,614,651,522,687,414
            142,288,245,746,800,326,506,779,553,193,530,332,899,777,252,157,385,592,762,922
            862,719,942,877,474,161,596,827,107,144,653,108,689,930,114,650,605,520,423,978
            628,889,631,772,930,381,917,746,58,861,488,162,948,765,486,117,927,21,112,936
            387,16,65,137,862,195,53,670,859,193,722,87,379,59,839,758,563,815,288,82
            427,901,429,81,78,535,652,245,179,880,197,318,286,643,837,809,143,935,245,78
            239,651,834,288,807,809,921,388,605,24,379,752,140,862,70,562,804,313,87,901
            875,669,225,480,777,835,917,802,928,179,102,302,482,79,620,850,487,616,94,592
            432,663,774,293,114,773,795,146,680,775,796,144,176,430,860,529,298,615,186,414
            761,561,668,321,603,816,775,58,752,281,222,597,302,797,813,741,327,657,691,676
            287,627,197,601,558,156,347,527,76,285,114,829,878,91,808,769,696,816,70,537
            878,286,456,762,903,683,534,485,611,420,451,307,17,395,949,784,453,384,107,937
            744,329,943,883,737,53,448,127,200,888,113,186,191,878,915,458,161,379,789,379
            898,829,622,527,289,984,133,822,731,457,811,115,191,717,423,785,822,455,782,591
            264,65,891,716,877,376,286,604,845,159,430,523,884,717,651,104,917,842,170,607
            931,789,110,173,327,833,920,566,492,922,812,530,97,286,808,284,861,453,620,523
            392,562,379,685,327,141,1,749,293,685,745,150,752,598,777,484,774,760,197,616
            358,930,790,915,507,442,807,326,945,901,776,417,610,83,289,258,634,831,878,444
            182,688,809,799,776,719,521,94,54,82,822,64,778,18,379,832,81,252,631,336
            925,800,668,161,303,716,783,714,943,138,140,459,9,669,190,193,677,522,740,76
            376,89,385,239,558,921,418,366,554,881,757,862,602,100,195,76,940,667,764,97
            157,892,476,814,385,688,225,258,597,736,946,362,684,758,68,735,627,528,380,81
            585,781,79,84,288,421,454,827,67,306,527,831,488,127,444,694,335,691,777,173
            626,146,243,422,323,375,225,918,154,997,375,83,720,855,306,520,433,802,766,221
            796,284,247,418,984,727,886,779,813,137,90,718,81,632,807,801,477,238,329,738
            778,692,334,304,935,160,893,322,903,427,842,143,721,236,991,292,782,773,562,80
            528,755,522,893,485,934,528,459,808,490,603,655,809,738,455,191,78,916,163,74
            114,861,281,299,295,798,553,98,98,948,889,380,263,936,437,433,686,360,893,299
            80,192,236,383,377,809,947,52,916,707,594,474,67,818,742,806,487,824,73,176
            773,743,356,522,487,890,936,722,639,813,809,607,822,169,601,63,288,802,78,680
            237,288,732,945,938,771,369,876,481,69,223,829,426,694,894,140,874,336,109,762
            244,680,767,753,612,262,650,413,940,288,862,89,421,433,53,328,537,880,435,298
            58,358,786,934,328,507,916,196,263,994,831,945,60,594,521,731,858,826,302,236
            330,386,723,193,822,745,673,448,755,535,735,677,778,511,163,949,898,460,194,783
            816,608,735,430,775,823,841,91,237,558,931,251,634,541,163,786,109,172,903,334
            603,932,727,742,922,326,668,800,938,901,325,389,782,924,902,695,768,429,259,773
            709,522,805,588,477,172,897,439,920,889,382,104,739,804,619,261,165,251,317,115
            173,88,588,87,135,446,441,537,240,435,809,600,690,227,523,247,730,416,828,797
            55,136,924,674,359,256,861,194,715,790,149,307,686,889,414,797,827,64,185,782
            263,559,240,106,585,464,198,138,291,325,532,875,593,943,751,633,751,697,481,631
            155,722,381,862,290,939,507,888,719,941,748,111,221,890,239,60,192,510,250,789
            341,626,187,438,300,632,68,94,814,102,336,507,191,595,53,856,441,282,80,330
            177,826,283,592,302,238,666,716,70,466,285,172,423,246,59,584,673,308,287,158
            855,378,460,312,568,836,154,473,480,134,250,670,800,921,193,557,887,793,857,54
            905,800,171,936,75,168,474,318,875,76,89,920,322,181,383,386,716,165,904,289
            895,841,74,584,934,525,377,532,790,133,811,114,944,881,225,298,750,258,935,942
            671,918,720,841,242,387,882,698,558,389,688,187,161,727,439,315,622,610,725,892
            440,105,807,336,434,750,193,526,263,225,636,291,426,785,679,883,854,727,883,319
            307,799,80,838,446,836,438,195,108,482,241,992,791,763,529,380,193,743,389,184
            417,757,9,153,607,634,95,680,170,811,531,479,246,261,251,112,425,78,133,937
            460,145,178,478,454,159,52,611,242,597,299,617,260,991,196,592,157,756,668,923
            295,859,766,670,486,626,309,897,570,443,313,375,199,100,53,610,293,306,191,524
            757,835,818,112,782,774,428,787,532,432,51,450,689,391,673,796,84,694,644,689
            833,746,922,311,158,51,922,935,133,624,81,735,635,690,747,881,252,293,854,630
            782,766,691,50,65,787,607,765,793,123,811,99,170,376,133,928,59,196,90,832
            760,816,584,781,65,945,728,194,536,178,364,556,896,380,109,633,178,483,302,390
            478,670,724,478,835,184,488,848,814,619,666,822,650,62,737,321,878,64,322,185
            810,735,792,694,414,752,512,200,144,242,743,666,766,362,531,715,856,455,745,759
            381,697,779,531,716,224,136,798,659,163,144,532,860,379,888,436,933,799,527,794
            436,452,600,805,437,929,902,448,731,834,327,88,588,188,0,854,748,85,444,160
            605,730,991,751,108,177,171,527,778,889,305,69,396,396,555,614,162,935,78,605
            803,933,765,918,590,137,678,526,152,681,0,753,183,326,170,668,298,457,756,824
            840,732,564,184,526,721,886,215,200,95,876,673,737,288,892,835,454,186,817,779
            856,788,67,426,748,595,597,438,913,761,756,324,437,651,938,449,729,622,331,527
            894,684,375,669,839,802,745,599,222,64,190,807,946,307,891,401,252,876,555,947
            765,338,927,95,811,598,140,769,536,487,556,159,290,305,677,294,286,359,489,103
            226,628,697,157,115,592,623,524,61,696,423,170,63,456,858,181,674,325,634,484
            305,586,222,7,675,115,332,556,82,781,795,775,397,388,388,111,828,670,247,874
            684,855,80,719,142,416,171,586,722,255,609,734,426,830,323,110,282,904,824,182
            937,767,752,717,686,723,77,331,783,530,115,415,354,894,813,481,299,895,306,793
            360,433,678,284,601,652,98,556,20,52,818,482,246,302,938,379,173,288,155,292
            161,397,327,64,697,786,891,311,831,734,600,946,730,398,824,385,288,382,361,506
            112,458,91,156,165,347,833,530,141,610,918,473,189,165,320,450,687,455,300,93
            949,56,99,221,416,686,181,650,857,929,676,307,530,170,332,793,632,767,195,660
            583,7,835,146,789,96,156,521,329,397,222,876,738,476,806,183,460,525,193,317
            84,490,559,715,611,225,990,237,51,69,812,110,758,917,261,241,51,298,250,192
            472,790,817,745,325,555,584,454,631,675,878,854,826,440,263,620,556,770,937,583
            785,301,676,246,420,2,188,936,537,653,772,134,93,435,807,881,140,920,772,491
            854,901,182,945,836,170,768,441,824,478,874,361,244,769,393,762,244,400,915,287
            902,620,414,426,11,56,621,827,446,775,328,249,333,618,155,836,612,140,141,73
            260,101,727,876,537,621,462,437,381,65,697,285,506,302,625,679,382,313,377,375
            623,81,821,292,111,740,648,289,587,630,147,832,537,942,729,830,394,610,750,188
            608,716,457,172,438,108,617,94,819,80,312,74,311,70,804,263,625,893,411,359
            332,262,887,590,650,919,837,483,356,230,531,801,238,442,596,335,679,801,622,890
            223,665,718,881,527,714,779,772,72,595,487,738,55,328,585,526,181,560,368,772
            753,803,389,522,823,651,607,804,64,860,755,784,378,623,158,778,831,874,654,493
            60,83,99,330,387,802,895,133,289,658,133,101,484,665,452,157,928,533,335,824
            876,432,743,90,180,830,196,521,442,7,396,102,438,287,922,926,447,316,89,795
            826,185,153,441,683,214,329,529,89,595,947,337,792,337,894,688,883,262,426,166
            140,311,590,826,489,788,838,330,947,741,856,105,162,151,373,391,291,675,423,860
            489,622,489,190,297,240,610,657,159,597,670,754,282,916,675,678,768,587,825,245
            282,780,248,813,562,284,302,509,385,395,889,57,753,245,261,187,95,322,160,221
            357,422,140,819,729,830,458,749,811,55,475,386,803,626,104,366,291,724,441,284
            252,396,861,65,513,491,554,110,774,74,833,117,70,184,474,597,191,64,333,634
            894,558,939,85,898,743,111,501,381,772,537,933,790,448,88,51,765,245,237,453
            193,96,759,822,506,362,424,610,341,225,111,456,159,923,555,595,306,444,677,875
            147,89,456,102,143,262,808,522,112,420,481,373,619,778,65,112,379,247,414,732
            786,918,780,684,367,829,144,58,854,589,449,282,251,200,633,761,140,931,246,621
            725,829,804,95,947,289,937,925,365,93,897,149,456,880,225,393,747,238,740,728
            557,58,89,527,423,714,483,71,170,99,694,593,78,437,148,9,383,449,606,161
            309,113,750,136,620,773,429,938,423,848,133,855,361,943,92,618,68,198,489,383
            494,747,170,181,440,139,418,717,251,219,362,882,833,323,417,760,802,489,163,526
            312,288,781,685,949,137,310,165,506,411,239,767,669,422,158,633,523,289,423,299
            301,100,237,815,884,423,263,720,138,280,767,393,98,789,609,90,613,441,238,802
            96,9,332,56,457,309,944,688,296,809,667,319,302,717,732,763,937,627,651,110
            363,584,949,251,154,152,506,616,949,681,394,861,763,429,676,604,945,776,758,223
            185,255,155,300,93,81,592,727,294,770,420,937,564,329,144,440,922,622,691,817
            670,447,673,527,531,899,724,334,594,246,298,769,511,841,448,629,100,285,159,880
            414,292,796,684,938,753,98,597,454,395,418,938,381,676,81,536,437,643,221,820
            296,766,387,453,422,584,86,604,828,95,60,184,584,365,102,325,613,947,895,526
            52,136,612,690,414,939,748,933,305,574,915,820,668,331,249,105,182,916,115,458
            127,375,222,879,388,817,169,898,454,615,146,73,237,64,222,831,812,460,239,783
            359,798,144,904,110,417,396,281,251,223,249,61,893,729,813,549,58,391,770,766
            311,480,322,925,753,526,217,771,619,685,85,63,163,537,859,388,329,593,920,314
            263,588,476,778,778,641,609,812,819,248,674,839,440,73,175,716,446,526,93,397
            521,665,583,92,176,308,380,807,68,491,281,3,753,793,99,251,236,756,691,309
            760,842,145,152,827,192,788,423,693,831,139,106,854,908,139,173,361,165,395,387
            633,282,561,828,827,812,582,285,178,294,478,378,177,295,194,80,874,741,180,257
            305,604,414,438,685,756,175,135,123,935,947,261,625,820,526,323,396,833,786,298
            169,487,506,252,588,432,426,430,24,605,593,282,247,727,612,727,391,478,90,114
            222,161,821,381,356,263,771,777,889,785,746,396,920,19,284,74,316,719,102,147
            524,423,326,935,286,442,659,288,391,103,305,678,805,304,294,941,736,948,242,749
            876,803,776,494,652,591,318,736,665,153,73,51,758,722,236,50,749,732,617,338
            261,859,260,188,436,629,87,900,238,189,525,310,360,344,263,827,386,143,532,752
            939,583,385,238,153,224,842,589,585,498,607,391,876,490,608,588,318,378,457,162
            917,652,370,198,808,808,822,894,932,172,154,335,390,156,292,476,174,378,750,375
            846,240,490,523,441,731,145,809,426,261,61,925,156,793,329,358,785,728,782,81
            258,750,616,630,807,150,942,608,299,438,68,440,245,824,611,921,915,591,247,555
            875,534,237,152,647,59,172,93,721,486,165,329,300,858,874,426,781,240,431,85
            524,485,76,261,673,632,571,79,433,152,106,936,744,945,87,833,632,166,309,460
            113,314,530,927,477,658,444,606,165,588,72,738,875,459,756,733,738,189,238,793
            457,195,691,877,308,993,387,610,618,884,840,139,426,947,816,732,236,393,384,819
            614,307,298,667,430,305,854,397,596,436,808,559,199,701,163,617,817,787,321,740
            879,76,139,827,441,334,592,434,162,284,446,886,899,613,449,287,696,161,350,630
            279,862,633,428,744,174,695,765,105,321,602,136,884,77,925,241,223,885,197,715
            198,113,51,763,788,995,812,678,107,884,823,835,108,589,424,732,221,627,796,137
            86,585,821,584,679,152,197,874,183,439,476,92,326,784,141,649,283,162,195,429
            931,653,901,242,583,174,779,795,814,899,518,923,309,769,84,287,767,87,684,738
            558,179,64,415,375,535,331,842,555,808,52,301,144,251,488,344,64,140,300,838
            188,391,116,437,88,172,897,752,515,891,224,900,674,925,64,623,804,668,64,749
            834,54,948,444,55,389,328,878,80,763,301,856,884,477,666,798,949,574,419,320
            829,943,690,535,55,545,933,356,194,297,240,536,336,200,937,562,523,774,148,286
            286,733,387,690,821,370,238,419,611,417,331,728,177,775,290,437,162,431,310,585
            782,561,773,172,818,112,604,728,107,54,896,629,675,782,622,513,477,731,885,260""";

    public static final String INPUT3 = """
            departure class: 0-1 or 4-19
            row: 0-5 or 8-19
            departure seat: 0-13 or 16-19
                        
            your ticket:
            11,12,13
                        
            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
            """;

    @Test
    public void testPart101() {
        final List<String> input = getAsList(INPUT1);
        final Day day = new Day16(input);
        assertEquals(71, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = getAsList(INPUT2);
        final Day day = new Day16(input);
        assertEquals(20231, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList(INPUT3);

        final Day day = new Day16(input);
        assertEquals(12 * 13, day.solvePart2());
    }

    @Test
    public void testPart202() {
        final List<String> input = getAsList(INPUT2);

        final Day day = new Day16(input);
        assertEquals(1940065747861L, day.solvePart2());
    }


}
