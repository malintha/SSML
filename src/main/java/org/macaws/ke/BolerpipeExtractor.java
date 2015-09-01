package org.macaws.ke;

import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

import java.net.URL;

/**
 * Created by Malintha on 8/10/2015.
 */
public class BolerpipeExtractor {
    public static void main(String[] args) throws Exception {
/**
 * todo : https://github.com/kohlschutter/boilerpipe/blob/master/boilerpipe-common/src/main/java/com/kohlschutter/boilerpipe/sax/HTMLFetcher.java#L82
 * boilerplate's html fetcher also uses direct html code to get the text. it reads html code as an input stream.
 * https://github.com/kohlschutter/boilerpipe/blob/master/boilerpipe-common/src/main/java/com/kohlschutter/boilerpipe/extractors/ExtractorBase.java#L82
 *
 */
//        String htmlCode = "<!DOCTYPE html>\n" +
//                "<!-- hostname: wci027, country: lk, cluster: sl, created: 2015-08-25 13:51:41 -->\n" +
//                "<!--[if IE 8]><html class=\"lt-ie9\" lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:fb=\"http://www.facebook.com/2008/fbml\"> <![endif]-->\n" +
//                "<!--[if IE 9]><html class=\"ie9\" lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:fb=\"http://www.facebook.com/2008/fbml\"> <![endif]-->\n" +
//                "<!--[if gt IE 9]><!--> <html lang=\"en\"> <!--<![endif]-->\n" +
//                "<head>\n" +
//                "    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
//                "    <script type=\"text/javascript\">var _sf_startpt=(new Date()).getTime()</script>\n" +
//                "    <meta name=\"google-site-verification\" content=\"ZxdgH3XglRg0Bsy-Ho2RnO3EE4nRs53FloLS6fkt_nc\" />\n" +
//                "    <title>Ask Steven: Gilly's no-balls, and Rahane's catches |  Cricket  | ESPN Cricinfo</title>\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
//                "    \n" +
//                "    <meta name=\"keywords\" content=\"Moeen Ali, Ajinkya Rahane, Arthur Morris, Australia cricket, England cricket, India cricket\" />\n" +
//                "    \n" +
//                "    <meta name=\"description\" content=\"Also: Moeen Ali's Ashes distinction, other 3-2 Ashes scorelines, and the oldest living Australian players\" />\n" +
//                "\n" +
//                "    <!-- Include isMobile script inline -->\n" +
//                "    <script>\n" +
//                "/**\n" +
//                " * isMobile.js v0.3.6\n" +
//                " *\n" +
//                " * @author: Kai Mallea (kmallea@gmail.com)\n" +
//                " *\n" +
//                " * @license: http://creativecommons.org/publicdomain/zero/1.0/\n" +
//                " */\n" +
//                "!function(a){var b=/iPhone/i,c=/iPod/i,d=/iPad/i,e=/(?=.*\\bAndroid\\b)(?=.*\\bMobile\\b)/i,f=/Android/i,g=/IEMobile/i,h=/(?=.*\\bWindows\\b)(?=.*\\bARM\\b)/i,i=/BlackBerry/i,j=/BB10/i,k=/Opera Mini/i,l=/(?=.*\\bFirefox\\b)(?=.*\\bMobile\\b)/i,m=new RegExp(\"(?:Nexus 7|BNTV250|Kindle Fire|Silk|GT-P1000)\",\"i\"),n=function(a,b){return a.test(b)},o=function(a){var o=a||navigator.userAgent;return this.apple={phone:n(b,o),ipod:n(c,o),tablet:n(d,o),device:n(b,o)||n(c,o)||n(d,o)},this.android={phone:n(e,o),tablet:!n(e,o)&&n(f,o),device:n(e,o)||n(f,o)},this.windows={phone:n(g,o),tablet:n(h,o),device:n(g,o)||n(h,o)},this.other={blackberry:n(i,o),blackberry10:n(j,o),opera:n(k,o),firefox:n(l,o),device:n(i,o)||n(j,o)||n(k,o)||n(l,o)},this.seven_inch=n(m,o),this.any=this.apple.device||this.android.device||this.windows.device||this.other.device||this.seven_inch,this.phone=this.apple.phone||this.android.phone||this.windows.phone,this.tablet=this.apple.tablet||this.android.tablet||this.windows.tablet,\"undefined\"==typeof window?this:void 0},p=function(){var a=new o;return a.Class=o,a};\"undefined\"!=typeof module&&module.exports&&\"undefined\"==typeof window?module.exports=o:\"undefined\"!=typeof module&&module.exports&&\"undefined\"!=typeof window?module.exports=p():\"function\"==typeof define&&define.amd?define(\"isMobile\",[],a.isMobile=p()):a.isMobile=p()}(this);\n" +
//                "</script>\n" +
//                "\n" +
//                "\n" +
//                "<!--[if IE 9]>\n" +
//                "<script language=\"javascript\" type=\"text/javascript\">\n" +
//                "function fnCreateJumpList(iScenario) {\n" +
//                "fnClearJumpList();\n" +
//                "window.external.msSiteModeCreateJumpList(\"Quick Links\")\n" +
//                "window.external.msSiteModeAddJumpListItem('Cricket Monthly', 'http://www.espncricinfo.com/ci/content/current/url/784439.html', '/favicon.ico');window.external.msSiteModeAddJumpListItem('Shop', 'http://www.espncricinfo.com/ci/content/current/url/738963.html', '/favicon.ico');window.external.msSiteModeAddJumpListItem('SA v NZ', 'http://www.espncricinfo.com/zimbabwe-south-africa-v-new-zealand-2015/content/current/series/848811.html', '/favicon.ico');window.external.msSiteModeAddJumpListItem('SL v Ind', 'http://www.espncricinfo.com/sri-lanka-v-india-2015/content/current/series/895057.html', '/favicon.ico');window.external.msSiteModeAddJumpListItem('SL v Pak', 'http://www.espncricinfo.com/sri-lanka-v-pakistan-2015/content/current/series/860091.html', '/favicon.ico');window.external.msSiteModeAddJumpListItem('Farewell Sanga', 'http://www.espncricinfo.com/srilanka/content/current/story/910829.html', '/favicon.ico');window.external.msSiteModeShowJumpList();\n" +
//                "}\n" +
//                "function fnClearJumpList() {\n" +
//                "window.external.msSiteModeClearJumplist();\n" +
//                "}\n" +
//                "</script>\n" +
//                "\n" +
//                "<meta name=\"msapplication-task\" content=\"name=Live Scores;action-uri=http://www.espncricinfo.com/ci/engine/current/match/scores/live.html;icon-uri=/favicon.ico\"/>\n" +
//                "<meta name=\"msapplication-task\" content=\"name=Latest News;action-uri=http://www.espncricinfo.com/ci/content/current/story/news.html;icon-uri=/favicon.ico\"/>\n" +
//                "<meta name=\"msapplication-task\" content=\"name=Fixtures;action-uri=http://www.espncricinfo.com/ci/content/current/match/fixtures/index.html;icon-uri=/favicon.ico\"/>\n" +
//                "<meta name=\"msapplication-task\" content=\"name=Results;action-uri=http://www.espncricinfo.com/ci/engine/current/match/scores/recent.html;icon-uri=/favicon.ico\"/>\n" +
//                "<meta name=\"msapplication-task\" content=\"name=Photos;action-uri=http://www.espncricinfo.com/ci/content/current/image/index.html;icon-uri=/favicon.ico\"/>\n" +
//                "<meta name=\"msapplication-task\" content=\"name=Audio/Video;action-uri=http://www.espncricinfo.com/ci/content/video_audio/index.html;icon-uri=/favicon.ico\"/>\n" +
//                "<script language=\"javascript\" type=\"text/javascript\">\n" +
//                "        fnCreateJumpList(2);\n" +
//                "</script>\n" +
//                "<![endif]-->\n" +
//                "<meta name=\"robots\" content=\"index, follow\" />\n" +
//                "<meta name=\"googlebot\" content=\"index, follow\" />\n" +
//                "<meta property=\"fb:app_id\" content=\"260890547115\" />\n" +
//                "<meta property=\"og:site_name\" content=\"Cricinfo\" />\n" +
//                "<link rel=\"shortcut icon\" href=\"/favicon.ie9new.ico\" />\n" +
//                "<link rel=\"icon\" type=\"image/png\" href=\"/favicon.png\" />\n" +
//                "<link rel=\"icon\" type=\"image/gif\" href=\"/favicon.gif\" />\n" +
//                "<link rel=\"apple-touch-icon\" href=\"http://i.imgci.com/espncricinfo/ci_apple_webclip.png\"/>\n" +
//                "<meta name=\"application-name\" content=\"ESPNcricinfo\"/>\n" +
//                "<meta name=\"msapplication-TileColor\" content=\"#266ab4\"/>\n" +
//                "<meta name=\"msapplication-TileImage\" content=\"http://i.imgci.com/espncricinfo/6b245241-3938-499c-8c79-9b80f97bed96.png\"/>\n" +
//                "\n" +
//                "\n" +
//                "<meta property=\"article:published_time\" content=\"2015-08-25T06:19:44+00:00\"/>\n" +
//                "<meta property=\"article:modified_time\" content=\"2015-08-25T10:07:18+00:00\"/>\n" +
//                "<meta name=\"DC.date.issued\" content=\"2015-08-25\">\n" +
//                "\n" +
//                "\n" +
//                "<meta property=\"og:type\" content=\"article\"/>\n" +
//                "<meta property=\"og:title\" content=\"Gilly's no-balls, and Rahane's catches\"/>\n" +
//                "<meta property=\"og:description\" content=\"Also: Moeen Ali's Ashes distinction, other 3-2 Ashes scorelines, and the oldest living Australian players \">\n" +
//                "<meta name=\"twitter:card\" value=\"summary_large_image\"> \n" +
//                "<meta name=\"twitter:site\" value=\"@espncricinfo\"> \n" +
//                "<meta name=\"twitter:creator\" value=\"@espncricinfo\">\n" +
//                "<meta name=\"twitter:title\" content=\"Gilly's no-balls, and Rahane's catches\">\n" +
//                "<meta name=\"twitter:description\" content=\"Also: Moeen Ali's Ashes distinction, other 3-2 Ashes scorelines, and the oldest living Australian players \">\n" +
//                "\n" +
//                "<link rel=\"image_src\" href=\"http://www.espncricinfo.com/db/PICTURES/CMS/218000/218057.jpg\" />\n" +
//                "<meta property=\"og:image\" content=\"http://www.espncricinfo.com/db/PICTURES/CMS/218000/218057.jpg\" />\n" +
//                "<meta name=\"twitter:image:src\" content=\"http://www.espncricinfo.com/db/PICTURES/CMS/218000/218057.jpg\" />\n" +
//                "\n" +
//                "<meta name=\"twitter:image:width\" content=\"900\" />\n" +
//                "<meta name=\"twitter:image:height\" content=\"561\" />\n" +
//                "\n" +
//                "<link rel=\"canonical\" href=\"http://www.espncricinfo.com/magazine/content/story/913301.html\"/>\n" +
//                "<meta property=\"og:url\" content=\"http://www.espncricinfo.com/magazine/content/story/913301.html\" />\n" +
//                "\n" +
//                "<!-- Load GPT JavaScript library used by DFP -->\n" +
//                "<script type=\"text/javascript\">\n" +
//                "  var googletag = googletag || {};\n" +
//                "  googletag.cmd = googletag.cmd || [];\n" +
//                "  (function() {\n" +
//                "    var gads = document.createElement(\"script\");\n" +
//                "    gads.async = true;\n" +
//                "    gads.type = \"text/javascript\";\n" +
//                "    var useSSL = \"https:\" == document.location.protocol;\n" +
//                "    gads.src = (useSSL ? \"https:\" : \"http:\") + \"//www.googletagservices.com/tag/js/gpt.js\";\n" +
//                "    var node =document.getElementsByTagName(\"script\")[0];\n" +
//                "    node.parentNode.insertBefore(gads, node);\n" +
//                "   })();\n" +
//                "</script>\n" +
//                "<script type=\"text/javascript\" src=\"http://a.espncdn.com/combiner/c?js=swfobject/2.2/swfobject.js?minify=false\"></script>\n" +
//                "\n" +
//                "<style>\n" +
//                ".espni-ad-slot{line-height: 0;}\n" +
//                "pre.gpt-debug{padding: 10px; background: burlywood;}\n" +
//                "</style>\n" +
//                "<script type='text/javascript'> var ad_setting = {\"adtar\":\"\",\"kvauthor\":\"stevenlynch\",\"kvbrand\":\"magazine\",\"kvcluster\":\"sl\",\"kvnavtype\":\"story\",\"kvpt\":\"story\",\"kvsite\":\"magazine\",\"kvstoryid\":\"913301\",\"networkid\":\"6444\",\"path\":\"/6444/espn.cricinfo.com/teams/magazine/story\",\"template\":\"desktop\",\"template_type\":\"responsive\"};</script>\n" +
//                "\n" +
//                "\n" +
//                "    \n" +
//                "\n" +
//                "\t<link rel=\"stylesheet\" href=\"http://a.espncdn.com/combiner/c?css=fonts/bentonsans.css,fonts/bentonsansbold.css,fonts/bentonsanslight.css,fonts/bentonsansmedium.css,fonts/bentonsanscond.css,fonts/bentonsanscondbold.css,fonts/bentonsanscondmedium.css\" type=\"text/css\" media=\"screen\" charset=\"utf-8\">\n" +
//                "    <link rel=\"stylesheet\" href=\"/navigation/cricinfo/ci/assets/css/main_nav.css?v=1437387541\" type=\"text/css\" media=\"screen\" charset=\"utf-8\">\n" +
//                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"/navigation/cricinfo/ci/assets/css/cilogin.css?v=1415115045\" media=\"all\" />\n" +
//                "\n" +
//                "<link rel=\"stylesheet\" href=\"/navigation/cricinfo/ci/redesign/css/storypage/story.css?v=1439291188\">\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "<link rel=\"stylesheet\" media=\"print\" href=\"/navigation/cricinfo/ci/redesign/css/common/print.css?v=1435297656\">\n" +
//                "\n" +
//                "    <!--[if lt IE 9]>\n" +
//                "        <script src=\"/navigation/cricinfo/ci/assets/js/plugins/html5.js\"></script>\n" +
//                "        <link rel=\"stylesheet\" href=\"/navigation/cricinfo/ci/redesign/css/common/ie8.css?v=1432640863\">\n" +
//                "    <![endif]-->\n" +
//                "    \n" +
//                "    <script src=\"/navigation/cricinfo/ci/assets/js/libs/modernizr-2.8.3.min.js\"></script>\n" +
//                "    \n" +
//                "    <script language=\"javascript\"  type=\"text/javascript\">\n" +
//                "                    cqanswer = 'lk';\n" +
//                "                    location_country = 'lk';\n" +
//                "            location_cluster = 'sl';\n" +
//                "        ord=Math.random()*10000000000000000;\n" +
//                "        var __hpad300_100Web = 1;\n" +
//                "        var ad_counter = 1;\n" +
//                "        var ad_counter_mobile = 1;\n" +
//                "        var devicenames = ['iPhone','BB10','Android','Windows Phone','Fennec','GoBrowser','NokiaBrowser','S40OviBrowser','SymbianOS','NokiaN73','Symbian','Opera Mini','Opera Mobi','Minimo','IEMobile','Mobile Safari','BlackBerry','sonyericsson'];\n" +
//                "    </script>\n" +
//                "\n" +
//                "\n" +
//                "<style type=\"text/css\">\n" +
//                "#adform-container { width: auto!important; }\n" +
//                "</style>\n" +
//                "</head>\n" +
//                "<body class=\" pagetype-storypage\">\n" +
//                "<div id=\"viewport\">\n" +
//                "\n" +
//                "\n" +
//                "    <div id=\"fb-root\"></div>\n" +
//                "\n" +
//                "    <!-- Win8 Prompt (existing code) -->\n" +
//                "    \n" +
//                "    <!-- Win8 Prompt (existing code) -->\n" +
//                "\n" +
//                "    <div class=\"hide-for-mob\">\n" +
//                "        \n" +
//                "    </div>\n" +
//                "\n" +
//                "    <div class=\"site-container ad-banner-1280\">\n" +
//                "        <div class=\"leaderboard-ad\">\n" +
//                "            <div class=\"espni-ad-slot ad-banner\" data-slot-type=\"banner\" data-kvpos=\"top\"></div>\n" +
//                "        </div>\n" +
//                "    </div>\n" +
//                "\t\n" +
//                "\t\n" +
//                "<section class=\"mainnav_wrap\" id=\"mainnav_wrap\">\n" +
//                "\t<div class=\"nav_inner\" id=\"nav_inner\">\n" +
//                "\n" +
//                "\t\t<div class=\"nav_btn\" id=\"nav_btn\">\n" +
//                "\t\t\t<span class=\"sp sp_nav\"></span>\n" +
//                "\t\t</div>\n" +
//                "\n" +
//                "\t\t<div class=\"ci_logo\" id=\"ci_logo\">\n" +
//                "\t\t\t\n" +
//                "\t\t\t<a href=\"/\">\n" +
//                "\t\t\t\n" +
//                "\t\t\t\t \n" +
//                "\t\t\t\t <img src=\"http://i.imgci.com/espncricinfo/redesign/ci_logo.png\">\n" +
//                "\t\t\t\t \t\t\n" +
//                "\t\t\t\t<span class=\"logo_txt\">ESPN Cricinfo</span>\n" +
//                "\t\t\t</a>\n" +
//                "\t\t</div>\n" +
//                "\n" +
//                "\t\t<nav class=\"main_nav\" id=\"main_nav\">\n" +
//                "\n" +
//                "\t\t\t<form class=\"srch_form srch_mobile\" name=\"Search\" id=\"Search\" data-ajax=\"false\">\n" +
//                "\t\t\t\t<div class=\"srch_wrap\">\n" +
//                "\t\t\t\t\t<input class=\"srch_txt\" placeholder=\"Search\" data-role=\"none\" />\n" +
//                "\t\t\t\t\t<button class=\"srch_btn\" data-role=\"none\"><span class=\"sp sp_srch2\"></span></button>\n" +
//                "\t\t\t\t</div>\n" +
//                "\t\t\t</form>\n" +
//                "\n" +
//                "\t\t\t<ul class=\"nav_grp\" id=\"nav_grp\">\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_live_scores\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_live_scores\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Live Scores</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/engine/match/index.html?view=live\" name=\"&lpos=header_livescores\">Live Scores</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Live Scores</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/match/index.html?view=live\" name=\"&lpos=header_livescores\">Live Scores Home</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/match/index.html?view=week\" name=\"&lpos=header_livescores\">Matches by week</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/series/index.html?view=month\" name=\"&lpos=header_livescores\">Matches by month</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/series/index.html?view=season\" name=\"&lpos=header_livescores\">Matches by season</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/series/index.html\" name=\"&lpos=header_livescores\">Match series archive</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/match/fixtures_futures.html\" name=\"&lpos=header_livescores\">Current and Future tours</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/match/index.html?view=calendar\" name=\"&lpos=header_livescores\">Upcoming international matches</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:openS('/ci/engine/match/scores/desktop.html','dsktpScrBrd')\" name=\"&lpos=header_livescores\">Desktop Scoreboard</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_series\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_series\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Series</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/match/fixtures_futures.html\" name=\"&lpos=header_series\">Series</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Series</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/the-ashes-2015/content/series/743911.html\" name=\"&lpos=header_series\">The Ashes (England v Australia)</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/zimbabwe-south-africa-v-new-zealand-2015/content/series/848811.html\" name=\"&lpos=header_series\">South Africa and Zimbabwe v New Zealand</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/sri-lanka-v-india-2015/content/series/895057.html\" name=\"&lpos=header_series\">Sri Lanka v India</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/county-cricket-2015/content/series/786877.html\" name=\"&lpos=header_series\">County Cricket 2015</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/womens-ashes-2015/content/series/798357.html\" name=\"&lpos=header_series\">Australia Women tour of England (The Ashes)</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/icc-womens-championship-2014-16/content/series/772563.html\" name=\"&lpos=header_series\">ICC Women's Championship</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/icc-intercontinental-cup-2015-17/content/series/870857.html\" name=\"&lpos=header_series\">ICC Intercontinental Cup</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/wcl-championship-2015-17/content/series/870869.html\" name=\"&lpos=header_series\">ICC WCL Championship</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/match/index.html?view=calendar\" name=\"&lpos=header_series\">More current and future series</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_countries\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_countries\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Countries</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/site/cricket_squads_teams/index.html\" name=\"&lpos=header_countries\">Countries</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Countries</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"subnav_grp\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/australia/content/team/2.html\" name=\"&lpos=header_countries\">Australia</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/bangladesh/content/team/25.html\" name=\"&lpos=header_countries\">Bangladesh</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/england/content/team/1.html\" name=\"&lpos=header_countries\">England</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/india/content/team/6.html\" name=\"&lpos=header_countries\">India</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/newzealand/content/team/5.html\" name=\"&lpos=header_countries\">New Zealand</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/pakistan/content/team/7.html\" name=\"&lpos=header_countries\">Pakistan</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/southafrica/content/team/3.html\" name=\"&lpos=header_countries\">South Africa</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/srilanka/content/team/8.html\" name=\"&lpos=header_countries\">Sri Lanka</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/westindies/content/team/4.html\" name=\"&lpos=header_countries\">West Indies</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/zimbabwe/content/team/9.html\" name=\"&lpos=header_countries\">Zimbabwe</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/afghanistan/content/team/40.html\" name=\"&lpos=header_countries\">Afghanistan</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/hkg/content/team/19.html\" name=\"&lpos=header_countries\">Hong Kong</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ireland/content/team/29.html\" name=\"&lpos=header_countries\">Ireland</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/png/content/team/20.html\" name=\"&lpos=header_countries\">PNG</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/scotland/content/team/30.html\" name=\"&lpos=header_countries\">Scotland</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/uae/content/team/27.html\" name=\"&lpos=header_countries\">UAE</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/other/content/site/207463.html\" name=\"&lpos=header_countries\">Other Countries</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/women/content/site/207455.html\" name=\"&lpos=header_countries\">Women's Cricket</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci-icc/content/site/297120.html\" name=\"&lpos=header_countries\">ICC</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/canada/content/team/17.html\" name=\"&lpos=header_countries\">Canada</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/kenya/content/team/26.html\" name=\"&lpos=header_countries\">Kenya</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/netherlands/content/team/15.html\" name=\"&lpos=header_countries\">Netherlands</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/nepal/content/team/33.html\" name=\"&lpos=header_countries\">Nepal</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/oman/content/team/37.html\" name=\"&lpos=header_countries\">Oman</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/usa/content/team/11.html\" name=\"&lpos=header_countries\">USA</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_news\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_news\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">News</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/news.html\" name=\"&lpos=header_news\">News</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">News</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"subnav_grp\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/news.html\" name=\"&lpos=header_news\">News Home</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs?genre=454\" name=\"&lpos=header_news\">The Buzz</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/url/643069.html\" name=\"&lpos=header_news\">The Surfer</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/infocus/content/story/infocus.html\" name=\"&lpos=header_news\">In Focus</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_features\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_features\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Features</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/features.html\" name=\"&lpos=header_features\">Features</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Features</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"subnav_grp\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<div class=\"subnav_title\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t\t\t\t\tWriters\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=31\" name=\"&lpos=header_features\">Sambit Bal</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=589\" name=\"&lpos=header_features\">Simon Barnes</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=367\" name=\"&lpos=header_features\">Daniel Brettig</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=261\" name=\"&lpos=header_features\">Ian Chappell</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=276\" name=\"&lpos=header_features\">Aakash Chopra</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=268\" name=\"&lpos=header_features\">Brydon Coverdale</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=373\" name=\"&lpos=header_features\">Ed Cowan</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=200\" name=\"&lpos=header_features\">Tony Cozier</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=390\" name=\"&lpos=header_features\">Martin Crowe</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=116\" name=\"&lpos=header_features\">George Dobell</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=383\" name=\"&lpos=header_features\">David Hopps</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=329\" name=\"&lpos=header_features\">Jarrod Kimber</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=375\" name=\"&lpos=header_features\">Ashley Mallett</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=272\" name=\"&lpos=header_features\">Sidharth Monga</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=315\" name=\"&lpos=header_features\">Firdose Moonda</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=384\" name=\"&lpos=header_features\">Mark Nicholas</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=597\" name=\"&lpos=header_features\">Ricky Ponting</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=380\" name=\"&lpos=header_features\">Ed Smith</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=291\" name=\"&lpos=header_features\">Rob Steen</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=355\" name=\"&lpos=header_features\">Sharda Ugra</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/author.html?author=311\" name=\"&lpos=header_features\">Andy Zaltzman</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=256\" name=\"&lpos=header_features\">Guest Column</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<div class=\"subnav_title\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t\t\t\t\tSections\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=6\" name=\"&lpos=header_features\">Ask Steven</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=490\" name=\"&lpos=header_features\">Couch Talk</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=268\" name=\"&lpos=header_features\">Diaries</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=169\" name=\"&lpos=header_features\">Features</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=174\" name=\"&lpos=header_features\">Gleanings</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=188\" name=\"&lpos=header_features\">I Was There</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=175\" name=\"&lpos=header_features\">Interviews</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/magazine/content/story/magazine/otd.html\" name=\"&lpos=header_features\">On This Day</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=176\" name=\"&lpos=header_features\">Profiles</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=12\" name=\"&lpos=header_features\">Numbers Game</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=340\" name=\"&lpos=header_features\">Quick Singles</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/magazine/content/quote/index.html\" name=\"&lpos=header_features\">Quote Unquote</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=21\" name=\"&lpos=header_features\">Reviews</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=14\" name=\"&lpos=header_features\">Rewind</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/genre.html?genre=173\" name=\"&lpos=header_features\">Talking Cricket</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/thestands/content/site/thestands\" name=\"&lpos=header_features\"><b>The Stands</b></a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/story/editors_pick.html\" name=\"&lpos=header_features\"><b>EDITOR'S PICKS</b></a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_videos\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_videos\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Videos</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html\" name=\"&lpos=header_videos\">Videos</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Videos</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"subnav_grp\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html\" name=\"&lpos=header_videos\">Videos Home</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=21\" name=\"&lpos=header_videos\">Bowl at Boycs</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=10\" name=\"&lpos=header_videos\">Comments</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=46\" name=\"&lpos=header_videos\">Features</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=59\" name=\"&lpos=header_videos\">The Huddle</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=9\" name=\"&lpos=header_videos\">Interviews</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/legends-of-cricket/content/site/451900.html\" name=\"&lpos=header_videos\">Legends of Cricket</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=129\" name=\"&lpos=header_videos\">Let's Talk About</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=1\" name=\"&lpos=header_videos\">Match Analysis</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"subnav_grpitm\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=16\" name=\"&lpos=header_videos\">Match Point</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/modern_masters/index.html\" name=\"&lpos=header_videos\">Modern Masters</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=34\" name=\"&lpos=header_videos\">News and Analysis</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=119\" name=\"&lpos=header_videos\">Polite Enquiries</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=2\" name=\"&lpos=header_videos\">Press Conference</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=27\" name=\"&lpos=header_videos\">Switch Hit</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/video_audio/index.html?genre=127\" name=\"&lpos=header_videos\">Up for a Challenge</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.youtube.com/ESPNCricinfo\" target=\"_blank\" name=\"&lpos=header_videos\">YouTube</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/site/ontheroad/index.html\" name=\"&lpos=header_videos\">ONTHEROAD</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_blogs\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_blogs\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Blogs</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs/cordon.html\" name=\"&lpos=header_blogs\">Blogs</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Blogs</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs/cordon.html\" name=\"&lpos=header_blogs\">The Cordon</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs?genre=457\" name=\"&lpos=header_blogs\">The Confectionery Stall</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/thestands/content/site/thestands/genre.html?genre=451\" name=\"&lpos=header_blogs\">Inbox</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs/page2.html\" name=\"&lpos=header_blogs\">Page 2</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs?genre=463\" name=\"&lpos=header_blogs\">Shot Selection</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/blogs/content/story/blogs?genre=427\" name=\"&lpos=header_blogs\">Tour Diaries</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_photos\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_photos\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Photos</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/image/index.html\" name=\"&lpos=header_photos\">Photos</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Photos</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/image/index.html\" name=\"&lpos=header_photos\">Photos Home</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/gallery/index.html\" name=\"&lpos=header_photos\">Galleries</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"nav_grpitm nav_stats\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"sp sp_stats\"></span>\n" +
//                "\t\t\t\t\t\t\t\t\t<span class=\"nav_txt\">Stats</span>\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"/ci/content/stats/index.html\" name=\"&lpos=header_stats\">Stats</a>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t\t<div class=\"nav_ttl\">Stats</div>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/stats/index.html\" name=\"&lpos=header_stats\">Stats home</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/stats/index.html\" name=\"&lpos=header_stats\">Statsguru</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/records/index.html?id=2015;type=year\" name=\"&lpos=header_stats\">2015 records</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/records/index.html\" name=\"&lpos=header_stats\">All records</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/engine/series/index.html\" name=\"&lpos=header_stats\">Match and series archive</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/player/index.html\" name=\"&lpos=header_stats\">Players</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/ci/content/ground/index.html\" name=\"&lpos=header_stats\">Grounds</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/rankings/content/page/211271.html\" name=\"&lpos=header_stats\">Team Rankings / Player Rankings</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.espncricinfo.com/wisdenalmanack/content/story/almanack\" name=\"&lpos=header_stats\">Wisden Almanack</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t<a href=\"/insights\" name=\"&lpos=header_stats\">Insights</a>\n" +
//                "\t\t\t\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t<li class=\"nav_grpitm nav_shop\">\n" +
//                "\t\t\t\t<a href=\"http://shop.espncricinfo.com/\">\n" +
//                "\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t<span class=\"sp sp_shop\"></span>\n" +
//                "\t\t\t\t\t\t<span class=\"nav_txt\">Shop</span>\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t</a>\n" +
//                "\n" +
//                "\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t<ul class=\"sub_nav\">\n" +
//                "\t\t\t\t\t\t\t<li class=\"sub_nav_item\">\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"http://shop.espncricinfo.com/\" target=\"_blank\" name=\"&lpos=header_shop\">Shop</a>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</li>\t\t\t\t\t\t\t\n" +
//                "\n" +
//                "\n" +
//                "\t\t\t\t<!-- Right side section starts -->\n" +
//                "\t\t\t\t\n" +
//                "\t\t\t\t<li class=\"nav_grpitm nav_grp_usr\">\n" +
//                "\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t<span class=\"sp sp_usr\"></span>\n" +
//                "\t\t\t\t\t\t<span class=\"nav_txt\">User</span>\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t\n" +
//                "<script type=\"text/javascript\">\n" +
//                "\tvar endpoint = \"submit.espncricinfo.com\";\n" +
//                "\tvar server = \"www.espncricinfo.com\";\n" +
//                "</script>\n" +
//                "\n" +
//                "<div class=\"ci-logged-in-wrap\">\n" +
//                "\t<div class=\"ci-profile\">\n" +
//                "\t\t<div class=\"profile-image\"></div>\n" +
//                "\t\t<div class=\"profile-name\"></div>\n" +
//                "\t\t<!-- <a href=\"#\" class=\"insider\"><span class=\"sp sp_insider\"></span> Activate Insider</a> -->\n" +
//                "\t</div>\n" +
//                "\t<!-- <div class=\"setting\"><span class=\"sp sp_sett\"></span></div> -->\n" +
//                "\t<div class=\"ci-login-clear\"></div>\n" +
//                "\t<ul class=\"acc_nav acc_nav_v1\" style=\"float: left;\">\n" +
//                "\t\t<li class=\"acc_navitm\">\n" +
//                "\t\t\t<ul class=\"acc_navlnk\">\n" +
//                "\t\t\t\t<li class=\"edit-profile\"><a href=\"/member_mgmt/content/submit/member_mgmt/user_registration.html\">Edit Profile</a></li>\n" +
//                "\t\t\t\t<!-- <li class=\"profile-settings\"><a href=\"#\">Settings</a></li> -->\n" +
//                "\t\t\t\t<li class=\"profile-signout\"><a href=\"#\">Sign Out</a></li>\n" +
//                "\t\t\t</ul>\n" +
//                "\t\t</li>\n" +
//                "\t</ul>\n" +
//                "</div>\n" +
//                "<div class=\"ci-login-form-wrap\">\n" +
//                "\t<form method=\"post\" name=\"ciloginfm\" id=\"cinavloginfm\" class=\"ci-login-form\">\n" +
//                "\t\t<div class=\"message\"></div>\n" +
//                "\t\t<div class=\"input-wrap\">\n" +
//                "\t\t\t<label for=\"email\">Email</label>\n" +
//                "\t\t\t<input name=\"email\" class=\"email textbox\" type=\"email\" placeholder=\"Enter your Email\" />\n" +
//                "\t\t</div>\n" +
//                "\t\t<div class=\"input-wrap\">\n" +
//                "\t\t\t<label for=\"password\">Password</label>\n" +
//                "\t\t\t<input name=\"password\" class=\"password textbox\" type=\"password\" placeholder=\"Password\" />\n" +
//                "\t\t</div>\n" +
//                "\t\t<div class=\"input-wrap remember\">\n" +
//                "\t\t\t<input name=\"remember\" class=\"remember\" type=\"checkbox\" value=\"Y\">\n" +
//                "\t\t\t<label for=\"remember\">Remember me</label>\n" +
//                "\t\t</div>\n" +
//                "\t\t<div class=\"input-wrap submit-button\">\n" +
//                "\t\t\t<input type=\"submit\" class=\"ci-login-submit-btn\" value=\"Sign In\" name=\"login_submit\" /> \n" +
//                "\t\t</div>\n" +
//                "\n" +
//                "\t\t<div class=\"input-wrap registration-links\">\n" +
//                "\t\t\t<a href=\"javascript:void(0)\" class=\"forgotpwdtxt\" onclick=\"javascript:window.open('/ci/content/submit/auth/web/forgotpassword.html','plyr_pop','width=600,height=390,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no');\">Forgot Password?</a> <span class=\"sep\">Or</span> \n" +
//                "\t\t\t<a href=\"/member_mgmt/content/submit/member_mgmt/user_registration.html?sc=masthead\">Register</a>\n" +
//                "\t\t</div>\n" +
//                "\t\t<div class=\"ci-login-clear\"></div>\n" +
//                "\t</form>\n" +
//                "\t<div class=\"social-login\">\n" +
//                "\t\t<div class=\"ci-login-btn facebook\">\n" +
//                "\t\t\t<!-- facebook login -->\n" +
//                "\t\t\t<div class=\"fb-login-button\" data-size=\"medium\" data-show-faces=\"false\" data-auto-logout-link=\"true\"></div>\n" +
//                "\t\t</div>\n" +
//                "\t</div>\n" +
//                "\t<div class=\"ci-login-clear\"></div>\n" +
//                "\t<div class=\"social-links\">\n" +
//                "\t\t<div class=\"slabel\">Follow Us </div>\n" +
//                "\t\t<div class=\"sbutton-wrap facebook\">\n" +
//                "\t\t\t<iframe src=\"//www.facebook.com/plugins/like.php?href=https%3A%2F%2Ffacebook.com%2FCricinfo&amp;width&amp;layout=button_count&amp;action=like&amp;show_faces=false&amp;share=false&amp;height=21&amp;appId=260890547115\" scrolling=\"no\" frameborder=\"0\" style=\"border:none; overflow:hidden; height:21px;\" allowTransparency=\"true\"></iframe>\n" +
//                "\t\t</div>\n" +
//                "\t\t<div class=\"sbutton-wrap gplus\">\n" +
//                "\t\t\t<!-- Place this tag in your head or just before your close body tag. -->\n" +
//                "\t\t\t<script src=\"https://apis.google.com/js/platform.js\" async defer></script>\n" +
//                "\n" +
//                "\t\t\t<!-- Place this tag where you want the widget to render. -->\n" +
//                "\t\t\t<div class=\"g-follow\" data-annotation=\"bubble\" data-height=\"24\" data-href=\"https://plus.google.com/106584820876356799712\" data-rel=\"publisher\"></div>\n" +
//                "\t\t</div>\n" +
//                "\t\t<div class=\"sbutton-wrap twitter\">\n" +
//                "\t\t\t<!--\n" +
//                "\t\t\t<iframe src=\"//platform.twitter.com/widgets/follow_button.html?screen_name=espncricinfo&lang=en\" style=\"width: 300px; height: 20px;\" allowtransparency=\"true\" frameborder=\"0\" scrolling=\"no\"></iframe> -->\n" +
//                "\t\t\t<a class=\"twitter-follow-button\" href=\"https://twitter.com/espncricinfo\" data-show-screen-name=\"false\" data-show-count=\"true\" data-lang=\"en\">Follow</a>\n" +
//                "\t\t\t<script type=\"text/javascript\">\n" +
//                "\t\t\twindow.twttr = (function (d, s, id) {\n" +
//                "\t\t\t  var t, js, fjs = d.getElementsByTagName(s)[0];\n" +
//                "\t\t\t  if (d.getElementById(id)) return;\n" +
//                "\t\t\t  js = d.createElement(s); js.id = id;\n" +
//                "\t\t\t  js.src= \"https://platform.twitter.com/widgets.js\";\n" +
//                "\t\t\t  fjs.parentNode.insertBefore(js, fjs);\n" +
//                "\t\t\t  return window.twttr || (t = { _e: [], ready: function (f) { t._e.push(f) } });\n" +
//                "\t\t\t}(document, \"script\", \"twitter-wjs\"));\n" +
//                "\t\t\t</script>\n" +
//                "\t\t</div>\n" +
//                "\t</div>\n" +
//                "</div>\n" +
//                "\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t<li class=\"nav_grpitm nav_grp_srch\">\n" +
//                "\t\t\t\t\t<div class=\"nav_grpico\">\n" +
//                "\t\t\t\t\t\t<span class=\"sp sp_srch1\"></span>\n" +
//                "\t\t\t\t\t\t<span class=\"nav_txt\">Search</span>\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t<div class=\"nav_grp_lvl1 dd_wrap\">\n" +
//                "\t\t\t\t\t\t<form class=\"srch_form\" name=\"Search\" id=\"Search\" data-ajax=\"false\">\n" +
//                "\t\t\t\t\t\t\t<div class=\"srch_wrap\">\n" +
//                "\t\t\t\t\t\t\t\t<input class=\"srch_txt\" placeholder=\"Search sports, team, players...\" />\n" +
//                "\t\t\t\t\t\t\t\t<button class=\"srch_btn\"><span class=\"sp sp_srch2\"></span></button>\n" +
//                "\t\t\t\t\t\t\t</div>\n" +
//                "\t\t\t\t\t\t</form>\n" +
//                "\t\t\t\t\t\t<ul class=\"acc_nav\">\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t<li class=\"acc_navitm most_want\">\n" +
//                "\t\t\t\t\t\t\t\t<div class=\"acc_nav_ttl\"> <span class=\"sp sp_most\"></span><a href=\"/ci/content/player/most_wanted.html\">Most Wanted Players</a></div>\n" +
//                "\t\t\t\t\t\t\t</li>\n" +
//                "\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t</ul>\n" +
//                "\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\n" +
//                "\t\t\t\t\n" +
//                "\t\t\t</ul>\t\n" +
//                "\n" +
//                "\t\t</nav>\n" +
//                "\n" +
//                "\t</div>\n" +
//                "</section>\n" +
//                "<section class=\"mainnav_wrap_small\" id=\"mainnav_wrap_small\">\n" +
//                "<!-- place holder for mobile navigation -->\n" +
//                "\t<div class=\"nav_mask\" id=\"nav_mask\"></div>\n" +
//                "</section>\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "<div class=\"si-wrap si-genre\">\n" +
//                "\t<div class=\"si-hdr\">\n" +
//                "\n" +
//                "\t<div class=\"heading-wrap\">\n" +
//                "\t\t\n" +
//                "\t\t<h1><a href=\"/magazine/content/story/genre.html?genre=6\">Ask Steven</a></h1>\n" +
//                "\t\t\n" +
//                "\t\t\n" +
//                "\t\t<h2>\n" +
//                "\t\tThe Tuesday column in which Steven Lynch answers your questions on all things cricket. Challenge him on <A href=\"http://www.facebook.com/asksteven\">Facebook</a> \n" +
//                "\t\t\n" +
//                "\t\t</h2>\n" +
//                "\t\t\n" +
//                "\t</div>\n" +
//                "\n" +
//                "\t\t<!--<div class=\"tweet\">\n" +
//                "\t\t\t<i class=\"sp sp_tweet\"></i>\n" +
//                "\t\t\t<span class=\"si-tweetname\">@author</span>\n" +
//                "\t\t</div>-->\t\t\t\n" +
//                "\n" +
//                "\n" +
//                "\t</div>\n" +
//                "</div>\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "    <!-- Homepage wrapper Start -->\n" +
//                "    <div class=\"story-page-wrapper\">\n" +
//                "\n" +
//                "        <div class=\"site-container\">\n" +
//                "\t\t\t<div class=\"pencil-ad\">\n" +
//                "\t<div class=\"slot-1 hide-for-mob\">\n" +
//                "\t</div>\n" +
//                "\t<div class=\"slot-2 hide-for-mob\" style=\"margin-right:0;\">\n" +
//                "\n" +
//                "\t</div>\n" +
//                "\n" +
//                "\n" +
//                "\t\t\t</div>\n" +
//                "\t\t</div>\n" +
//                "\n" +
//                "\t\t<div class=\"story-page-container\">\n" +
//                "\n" +
//                "\t\t<main class=\"primary\">\n" +
//                "\t\t\t<script type=\"text/javascript\">\n" +
//                "\tvar _isStory = 1;\n" +
//                "</script>\n" +
//                "\n" +
//                "\n" +
//                "<section class=\"col-1-1 main-content\">\n" +
//                "\t<article class=\"travel-lhs lhs\">\n" +
//                "\t\t<div class=\"main-story-container col-1-1\">\n" +
//                "\t\t<section class=\"story-headline col-10-12\">\n" +
//                "\n" +
//                "\n" +
//                "\t\t\t\t<span class=\"date col-3-12\">\n" +
//                "August 25, 2015\t\t\t\t\n" +
//                "\t\t\t\t</span>\n" +
//                "\t\t\t\t<h1 class=\"col-1-1\">Gilly's no-balls, and Rahane's catches</h1>\n" +
//                "\t\t\t\t\n" +
//                "\t\t\t\t<div class=\"author col-6-12\">\n" +
//                "\t\n" +
//                "\t\t\t\t\t<span class=\"author-image\"><img src=\"/db/PICTURES/CMS/103900/103936.1.png\" /></span>\n" +
//                "\n" +
//                "\t\t\t\t\t<span class=\"author-name\"><a href=\"/ci/content/story/author.html?author=34\">Steven Lynch</a></span>\n" +
//                "\n" +
//                "\t\t\t\t</div>\n" +
//                "\t\t\t\n" +
//                "\t\t\t\t<div class=\"facebook-twitter-wrap col-6-12\">\n" +
//                "\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t<a href=\"#\" class=\"facebook-share-button\"><span></span><b class=\"show-for-large\">Share on </b>Facebook</a>\n" +
//                "\t\t\t\t\t\t<a class=\"sep\"></a>\n" +
//                "\t\t\t\t\t\t<a href=\"#\" class=\"twitter-share-cricinfo\"><span></span><b class=\"show-for-large\">Share on</b> Twitter</a>\n" +
//                "\t\t\t\t\n" +
//                "\t\t\t\t\t\t\n" +
//                "\t\t\t\t</div>\n" +
//                "\n" +
//                "\t\t\t\t<div class=\"story-description col-1-1\">\n" +
//                "Also: Moeen Ali's Ashes distinction, other 3-2 Ashes scorelines, and the oldest living Australian players \n" +
//                "\t\t\t\t</div>\n" +
//                "\n" +
//                "\t\t</section>\t\t<section class=\"col-1-1 story-content-main\">\n" +
//                "\t\t\t\n" +
//                "\t\t\t\t<div class=\"fixed-social col-1-12 hide-for-small\">\n" +
//                "\t\t\t\t\t\n" +
//                "\t\t\t\t\t<span class=\"total-shares\">\n" +
//                "\t\t\t\t\t\t<b>&nbsp;</b>\n" +
//                "\t\t\t\t\t\t<span>shares</span>\n" +
//                "\t\t\t\t\t</span>\n" +
//                "\t\t\t\t\t\n" +
//                "\t\t\t\t\t<span class=\"facebook-shares sl\">\n" +
//                "\t\t\t\t\t\t<a href=\"#\" class=\"facebook-share-button\"></a>\n" +
//                "\t\t\t\t\t</span>\n" +
//                "\t\t\t\t\t<span class=\"twitter-shares\">\n" +
//                "\t\t\t\t\t\t<a href=\"#\" class=\"twitter-share-cricinfo\"></a>\n" +
//                "\t\t\t\t\t</span>\n" +
//                "\n" +
//                "\t\t\t\t\t<span class=\"print-shares\">\n" +
//                "\t\t\t\t\t\t<a href=\"/magazine/content/story/913301.html?wrappertype=print\"> </a>\n" +
//                "\t\t\t\t\t</span>\n" +
//                "\t\t\t\t</div>\n" +
//                "\t\t\n" +
//                "\t\t\t\t\t<p class=\"insert-para\">\n" +
//                "\t\t<figure class=\"video-section col-1-1 first-image\">\n" +
//                "\t\t\t<img src=\"/db/PICTURES/CMS/220900/220971.jpg\" />\n" +
//                "\t\t\t<figcaption>\n" +
//                "Reluctant bowler: Adam Gilchrist never bowled a ball in his 396 international matches\t\t\t\t<span class=\"copyright\"> &copy; Getty Images</span>\n" +
//                "\t\t\t</figcaption>\n" +
//                "\t\t</figure>\n" +
//                "\t</p>\n" +
//                "\n" +
//                "<p>\n" +
//                "<B>How many times has an Ashes series ended up 3-2?</B> <I>asked Martin Palmer from England</I> <BR>\n" +
//                "This one in 2015 was only the sixth Ashes series to end up 3-2, the first since <a href=\"http://www.espncricinfo.com/ci/engine/series/60645.html\">1997</a> when Australia came out on top. That, though, was a six-Test series which also included a draw: the last five-Test Ashes encounter to end up this way was the famous one of <a href=\"http://www.espncricinfo.com/ci/engine/series/60330.html\">1936-37</a>, when England went 2-0 up but Australia - skippered by Don Bradman - won the last three Tests to pinch the series, a unique feat. The other 3-2s were all in England's favour, in the 1884-85, 1894-95 and 1903-04 series in Australia.\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "<B>Moeen Ali scored nearly 300 runs in the Ashes series - is this a record for someone who never batted above No. 8?</B> <I>asked Martin Basterfield from England </I> <BR>\n" +
//                "<a href=\"http://www.espncricinfo.com/ci/content/player/8917.html\">Moeen Ali</a>'s 293 runs in the 2015 series from No. 8 or 9 has been exceeded only once in the Ashes, by Australia's <a href=\"http://www.espncricinfo.com/ci/content/player/4542.html\">Sammy Carter</a> - their wicketkeeper and an idiosyncratic batsman - who made 300 runs in the 1907-08 home series, with a highest score of 66. Carter's aggregate has been exceeded only twice in any Test series: Shaun Pollock made 302 runs in South Africa's five home Tests <a href=\"http://stats.espncricinfo.com/ci/engine/records/averages/batting_bowling_by_team.html?id=445;team=3;type=series\">against West Indies in 2000-01</a>, but he was shaded by Harbhajan Singh, who made 315 for India at home against New Zealand in <a href=\"http://stats.espncricinfo.com/ci/engine/records/averages/batting_bowling_by_team.html?id=5937;team=6;type=series\">2010-11</a>. That came from only three Tests: Harbhajan scored his only two Test hundreds, and averaged 105 overall.\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "<B>England won the fourth Test by an innings then lost the fifth by an innings. Has such a turnaround ever happened before?</B> <I>asked Neil Cartwright from England</I> <BR>\n" +
//                "This seesaw end to the 2015 series was only the second time in Ashes history that the sides had traded innings victories in successive Tests. The other occasion was <a href=\"http://www.espncricinfo.com/ci/engine/series/60413.html\">in 1965-66</a>, when England won the third Test in Sydney by an innings and 93 runs, only for Australia to hit back in Adelaide, winning by an innings and nine. In other series it has happened three times. In India <a href=\"http://www.espncricinfo.com/ci/engine/series/60355.html\">in 1952-53</a>, in Pakistan's inaugural official Test series, India won the first Test, by an innings in Delhi, only for Pakistan to turn the tables in Lucknow. This also happened in the series between England and West Indies <a href=\"http://www.espncricinfo.com/ci/engine/series/60415.html\">in 1966</a>, and the two-match rubber between India and South Africa <a href=\"http://www.espncricinfo.com/ci/engine/series/441824.html\">in 2009-10</a>.\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "<B>I know Ajinkya Rahane's eight catches in Galle was a Test best, but was it a first-class record too?</B> <I>asked Mahesh Rahul from India</I> <BR>\n" +
//                "<a href=\"http://www.espncricinfo.com/ci/content/player/277916.html\">Ajinkya Rahane</a>'s eight catches in the field in the recent first Test against Sri Lanka in Galle <a href=\"http://stats.espncricinfo.com/ci/content/records/283561.html\">eclipsed the old Test record</a> of seven, first achieved by Greg Chappell for Australia against England in Perth in 1974-75, and later equalled by Yajurvindra Singh (on debut, for India against England in Bangalore in 1976-77), Hashan Tillakaratne (Sri Lanka v New Zealand in Colombo, 1992-93), Stephen Fleming (New Zealand v Zimbabwe in Harare, 1997-98) and Matthew Hayden (Australia v Sri Lanka, also in Galle, in 2003-04). Rahane was the ninth outfielder to take five catches in a Test innings, a record originally set by Chappell's grandfather, Vic Richardson, against South Africa in <a href=\"http://www.espncricinfo.com/ci/engine/match/62637.html\">Durban</a> in 1935-36. The first-class record, though, is held by <a href=\"http://www.espncricinfo.com/ci/content/player/14022.html\">Wally Hammond</a>, who was a fine slip fielder as well as a superb batsman. Playing for Gloucestershire against Surrey at Cheltenham in 1928, Hammond took ten catches - eight of them off Charlie Parker - and also scored 139 in the first innings and 143 in the second.\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "\t<p class=\"insert-para\">\n" +
//                "\t\t<figure class=\"video-section col-1-1\">\n" +
//                "\t\t\t<img src=\"/db/PICTURES/CMS/218000/218057.jpg\" />\n" +
//                "\t\t\t<figcaption>\n" +
//                "Moeen Ali's 293 runs in Ashes 2015 is the second-highest by a No. 8 or below in an Ashes\t\t\t\t<span class=\"copyright\"> &copy; Getty Images</span>\n" +
//                "\t\t\t</figcaption>\n" +
//                "\t\t</figure>\n" +
//                "\t</p>\n" +
//                "\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "<B>Following the sad passing of Arthur Morris, who is Australia's oldest living Test cricketer?</B> <I>asked Jamie Stewart from Canada</I> <BR>\n" +
//                "The recent death of <a href=\"http://www.espncricinfo.com/ci/content/player/6644.html\">Arthur Morris</a>, aged 93, leaves 89-year-old <a href=\"http://www.espncricinfo.com/ci/content/player/6457.html\">Len Maddocks</a> as Australia's oldest living Test player. Maddocks, a wicketkeeper from Victoria, played seven Tests during the 1950s. Two of them came in England in 1956: Tony Lock inflicted a pair on him at Headingley, then he provided Jim Laker with two of his 19 wickets in the next match at Old Trafford. In the second innings, Maddocks was the last man out to complete Laker's ten-wicket haul. Morris' death means there is now just one survivor from the famous Australian \"Invincibles\" team of 1948, which was captained by Don Bradman. The last man standing is <a href=\"http://www.espncricinfo.com/ci/content/player/5603.html\">Neil Harvey</a>, who was only 19 during that tour and is now 86.\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "<B>Kumar Sangakkara played 594 international matches without ever bowling - is that a record?</B> <I>asked Sunit Kumar from Afghanistan </I> <BR>\n" +
//                "Well, it would have been a record - except that actually <a href=\"http://www.espncricinfo.com/ci/content/player/50710.html\">Kumar Sangakkara</a> did occasionally have a bowl, sending down 14 overs in Tests in four different innings. Ten of them - for a respectable 34 runs - came at Karachi in 2008-09, as Pakistan amassed 765 for 6 declared. The man who has played the most international matches without ever bowling is Adam Gilchrist, with 396, ahead of Moin Khan (288) and Ian Healy (287). The top non-wicketkeeper is Eoin Morgan, who has played 213 internationals so far without ever turning his arm over. Sangakkara did play more matches (594) than anyone else without taking a wicket: Gilchrist is next, ahead of Herschelle Gibbs, whose one and only over in 361 international matches came as the 11th bowler used when West Indies piled up 747 against South Africa in Antigua in 2004-05.\n" +
//                "</p>\t\t\t\t\n" +
//                "\t\t\t\t<div class=\"end-credit col-9-12\">\n" +
//                "\t\t\t\t\t<div>\n" +
//                "\t\t\t\t\t\t<p>Steven Lynch is the editor of the <i>Wisden Guide to International Cricket 2014</i>. <i>Ask Steven</i> is now on <a href=\"http://www.facebook.com/asksteven\" target=\"n\">Facebook</a></p>\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</div>\n" +
//                "\n" +
//                "\t\t\t\t<div class=\"end-copyright col-9-12\"> &#169; \n" +
//                "ESPN Sports Media Ltd.\n" +
//                "\t\t\t\t</div>\n" +
//                "\n" +
//                "<style type=\"text/css\">.ob-widget-header{font-family:bentonsansbold !important;}</style>\n" +
//                "<script type=\"text/javascript\">\n" +
//                "\t__espni_outbrain = {\"cluster\":\"sl\",\"desktop\":{\"ind\":\"AR_13\",\"uk\":\"AR_12\",\"usa\":\"AR_11\",\"www\":\"AR_13\"},\"mobile\":{\"ind\":\"MB_6\",\"uk\":\"MB_5\",\"usa\":\"MB_4\",\"www\":\"MB_6\"},\"outbrain_compliant\":\"Y\"};\n" +
//                "</script>\n" +
//                "<div class=\"outbrain-module col-9-12\">\n" +
//                "\t<div class=\"OUTBRAIN\" data-src=\"http://www.espncricinfo.com/magazine/content/story/913301.html\" data-widget-id=\"\" data-ob-template=\"espncricinfo\"></div>\n" +
//                "</div>\n" +
//                "<div class=\"ad-slot show-mobile\"><div class=\"espni-ad-slot ad-incontent\" data-slot-type=\"incontent\" data-kvpos=\"top\" data-exclude-bp=\"m,l,xl\"></div></div>\t\t\n" +
//                "\n" +
//                "\t\t</section>\t\n" +
//                "\t\t</div>\n" +
//                "\n" +
//                "\n" +
//                "<script language=\"javascript\">\n" +
//                "\tvar omniPageName = \"Gilly's no-balls, and Rahane's catches\";\n" +
//                "\tvar omniSiteSection1 = \"Cricinfo Magazine (online) site\";\n" +
//                "\tvar omniSiteSection2 = \"features\";\n" +
//                "\n" +
//                "\tvar omniCt = \"story\";\n" +
//                "\t\n" +
//                "\tvar omniAuthId = \"Steven Lynch\";\n" +
//                "\tvar omniStoryId = \"913301\";\n" +
//                "</script>\n" +
//                "\t</article>\t\n" +
//                "\t\n" +
//                "</section>\n" +
//                "\n" +
//                "\t\t</main>\n" +
//                "\n" +
//                "\t\t<aside class=\"secondary\">\n" +
//                "<div></div><!-- 1st MPU start: Excluding from small, medium profiles and only for story pages --><section class=\"ad-container\"><div class=\"espni-ad-slot ad-incontent\" data-slot-type=\"incontent\" data-kvpos=\"top\" data-exclude-bp=\"s,m\"></div></section><!-- 1st MPU end -->\t\t<section class=\"related_links\">\n" +
//                "\t\t\t\t\t<span class=\"lhs_header\">RELATED</span>\n" +
//                "\t\t\t\t\t\n" +
//                "\t\t\t\t\t<ul>\n" +
//                "\n" +
//                "\t\t\t\t<li>\n" +
//                "\t\t\t\t\t<div>\n" +
//                "\t\t\t\t\t\t<span>Players/Officials</span>\n" +
//                "\n" +
//                "\t\t\t\t\t<a href=\"/ci/content/player/8917.html\">Moeen Ali</a>\n" +
//                " | \n" +
//                "\t\t\t\t\t<a href=\"/ci/content/player/277916.html\">Ajinkya Rahane</a>\n" +
//                " | \n" +
//                "\t\t\t\t\t<a href=\"/ci/content/player/6644.html\">Arthur Morris</a>\n" +
//                "\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t<li>\n" +
//                "\t\t\t\t\t<div>\n" +
//                "\t\t\t\t\t\t<span>Teams</span>\n" +
//                "\n" +
//                "\t\t\t\t\t<a href=\"/australia/content/team/2.html\">Australia</a>\n" +
//                " | \n" +
//                "\t\t\t\t\t<a href=\"/england/content/team/1.html\">England</a>\n" +
//                " | \n" +
//                "\t\t\t\t\t<a href=\"/india/content/team/6.html\">India</a>\n" +
//                "\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</li>\n" +
//                "\n" +
//                "\t\t\t\t\t</ul>\n" +
//                "\t\t</section>\n" +
//                "\t\t\n" +
//                "\n" +
//                "\n" +
//                "<!-- 1st MPU start: Excluding from small, medium profiles and excluding the story pages -->\n" +
//                "<section class=\"ad-container\">\n" +
//                "\n" +
//                "</section>\n" +
//                "<!-- 1st MPU end -->\n" +
//                "\n" +
//                "<!-- 125x125 Ads start -->\n" +
//                "<section class=\"ad-container ad-140\">\n" +
//                "    <ul>\n" +
//                "\t<li>\n" +
//                "\n" +
//                "\t</li>\n" +
//                "\t<li>\n" +
//                "\t</li>\n" +
//                "</ul>\n" +
//                "</section>\n" +
//                "<!-- 125x125 Ads end -->\n" +
//                "\n" +
//                "<!-- Most Viewed module start -->\n" +
//                "<section class=\"most-viewed module\">\n" +
//                "    <div class=\"content-padding\">\n" +
//                "        <h5 class=\"section-name\">Most Viewed</h5>\n" +
//                "    </div>\n" +
//                "    <ul class=\"most-viewed-tabs\" data-contentparent=\"#most-viewed-content\">\n" +
//                "        <li class=\"selected\"><a href=\"#most-today\" class=\"ci-tabs\">Today</a></li>\n" +
//                "        <li><a href=\"#most-last7\" class=\"ci-tabs\">Last 7 Days</a></li>\n" +
//                "        <li><a href=\"#most-last30\" class=\"ci-tabs\">Last 30 Days</a></li>\n" +
//                "    </ul>\n" +
//                "    <div id=\"most-viewed-content\">\n" +
//                "        \n" +
//                "        <ul id=\"most-today\" class=\"content tabs-content\">\n" +
//                "        \n" +
//                "            <li><span class=\"count\">1</span><span class=\"text\"><a name=\"&lpos=mostviewed_1\" href=\"/sri-lanka-v-india-2015/content/story/912667.html\">Rahane, Ashwin lead India dominance</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">2</span><span class=\"text\"><a name=\"&lpos=mostviewed_2\" href=\"/sri-lanka-v-india-2015/content/story/912881.html\">Saha, Vijay ruled out with injury</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">3</span><span class=\"text\"><a name=\"&lpos=mostviewed_3\" href=\"/sri-lanka-v-india-2015/content/story/913107.html\">Ashwin takes five in commanding win</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">4</span><span class=\"text\"><a name=\"&lpos=mostviewed_4\" href=\"/the-ashes-2015/content/story/912791.html\">Victory sends off Clarke and Rogers on a high</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">5</span><span class=\"text\"><a name=\"&lpos=mostviewed_5\" href=\"/zimbabwe-south-africa-v-new-zealand-2015/content/story/912671.html\">NZ seamers, Guptill ton help level series</a></span></li>\n" +
//                "        \n" +
//                "        </ul>\n" +
//                "        \n" +
//                "        \n" +
//                "        <ul id=\"most-last7\" class=\"content tabs-content\">\n" +
//                "        \n" +
//                "            <li><span class=\"count\">1</span><span class=\"text\"><a name=\"&lpos=mostviewed_1\" href=\"/sri-lanka-v-india-2015/content/story/910653.html\">'We have all made fun of his accent'</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">2</span><span class=\"text\"><a name=\"&lpos=mostviewed_2\" href=\"/sri-lanka-v-india-2015/content/story/911249.html\">Rahul 108 shores up India on fluctuating day</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">3</span><span class=\"text\"><a name=\"&lpos=mostviewed_3\" href=\"/sri-lanka-v-india-2015/content/story/912667.html\">Rahane, Ashwin lead India dominance</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">4</span><span class=\"text\"><a name=\"&lpos=mostviewed_4\" href=\"/sri-lanka-v-india-2015/content/story/912881.html\">Saha, Vijay ruled out with injury</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">5</span><span class=\"text\"><a name=\"&lpos=mostviewed_5\" href=\"/sri-lanka-v-india-2015/content/story/913107.html\">Ashwin takes five in commanding win</a></span></li>\n" +
//                "        \n" +
//                "        </ul>\n" +
//                "        \n" +
//                "        \n" +
//                "        <ul id=\"most-last30\" class=\"content tabs-content\">\n" +
//                "        \n" +
//                "            <li><span class=\"count\">1</span><span class=\"text\"><a name=\"&lpos=mostviewed_1\" href=\"/sri-lanka-v-pakistan-2015/content/story/905537.html\">Afridi, Anwar Ali stun SL with one-wicket win</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">2</span><span class=\"text\"><a name=\"&lpos=mostviewed_2\" href=\"/the-ashes-2015/content/story/906795.html\">Broad and Root bury feeble Australia</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">3</span><span class=\"text\"><a name=\"&lpos=mostviewed_3\" href=\"/pakistan/content/story/906539.html\">Wasim Akram safe after being shot at in Karachi</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">4</span><span class=\"text\"><a name=\"&lpos=mostviewed_4\" href=\"/sri-lanka-v-india-2015/content/story/909771.html\">Herath seven razes India for 112</a></span></li>\n" +
//                "        \n" +
//                "            <li><span class=\"count\">5</span><span class=\"text\"><a name=\"&lpos=mostviewed_5\" href=\"/sri-lanka-v-india-2015/content/story/908601.html\">Ashwin six-for puts India in charge</a></span></li>\n" +
//                "        \n" +
//                "        </ul>\n" +
//                "        \n" +
//                "    </div>\n" +
//                "</section>\n" +
//                "<!-- Most Viewed module end -->\n" +
//                "<!-- 2nd MPU start -->\n" +
//                "<section class=\"ad-container\">\n" +
//                "    <div class=\"espni-ad-slot ad-incontent\" data-slot-type=\"incontent\" data-kvpos=\"bottom\" data-exclude-bp=\"s,m\"></div>\n" +
//                "</section>\n" +
//                "<!-- MPU end -->\n" +
//                "<!-- Sponsor Links module start -->\n" +
//                "<section class=\"sponsor-links module\">\n" +
//                "    <div class=\"content-padding\">\n" +
//                "        <h5 class=\"section-name\">Sponsored Links</h5>\n" +
//                "    </div>\n" +
//                "    <ul class=\"content\">\n" +
//                "\t\t<li>\n" +
//                "       \t\t<p class=\"link-text\"><a href=\"/ci/content/url/907041.html\" target=\"_blank\">Gold Price falls 13% in 2 Days!</a></p>\n" +
//                "            <p class=\"text\">Now is the time to Trade online with a welcome bonus up to $2,000!</p>\n" +
//                "        </li>  \n" +
//                "\t\t<li>\n" +
//                "       \t\t<p class=\"link-text\"><a href=\"/ci/content/url/907051.html\" target=\"_blank\">Risk Free</a></p>\n" +
//                "            <p class=\"text\">Practice your trading strategies on a FREE and UNLIMITED $100,000 practice account!</p>\n" +
//                "        </li>  \n" +
//                "\n" +
//                "\t</ul>\n" +
//                "</section>\n" +
//                "<!-- Sponsor Links module end --> \n" +
//                "<!-- Cricket on Twitter start -->\n" +
//                "<section class=\"reader_rec\">\n" +
//                "\t<a class=\"twitter-timeline\" href=\"/ESPNcricinfo/timelines/585813293161406464\" data-widget-id=\"586402209283227648\">Readers recommend</a> \n" +
//                "\t<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>\n" +
//                "</section>\n" +
//                "<!-- Cricket on Twitter end -->\n" +
//                "\t\t</aside>\n" +
//                "\t\t</div>\n" +
//                "\n" +
//                "    </div>\n" +
//                "    <!-- Homepage wrapper End -->\n" +
//                "\n" +
//                "    <!-- Footer Start -->\n" +
//                "    <div class=\"site-container\">\n" +
//                "    <footer>\n" +
//                "    <div>\n" +
//                "        <div class=\"footer-links\">\n" +
//                "            <ul class=\"horizontal\">\n" +
//                "                <li><a href=\"/ci/content/page/866033.html\" class=\"maintabs ui-link\">Sitemap</a></li>\n" +
//                "                <li><a href=\"/ci/content/submit/forms/feedback.html\" class=\"maintabs ui-link\">Feedback</a></li>\n" +
//                "                <li><a href=\"/ci/content/rss/feeds_rss_cricket.html\" class=\"maintabs ui-link\">RSS</a></li>\n" +
//                "                <li><a href=\"/ci/content/page/156066.html\" class=\"maintabs ui-link\">About Us</a></li>\n" +
//                "                <li><a href=\"/ci/content/site/careers/careers.html\" class=\"maintabs ui-link\">Careers</a></li>\n" +
//                "                <!-- <li><a href=\"/ci/content/page/407211.html\" class=\"maintabs ui-link\">Advertise</a></li> -->\n" +
//                "                <li><a href=\"http://disneyprivacycenter.com/\" class=\"maintabs ui-link\" target=\"_blank\">Privacy Policy</a></li>\n" +
//                "                <li><a href=\"http://disneytermsofuse.com/\" class=\"maintabs ui-link\" target=\"_blank\">Terms of Use</a></li>\n" +
//                "            </ul>\n" +
//                "        </div>\n" +
//                "        <div class=\"footer-other-sites\">\n" +
//                "            <div class=\"logo-sprite\">\n" +
//                "                <img src=\"http://i.imgci.com/espncricinfo/redesign/espn-family-logo_3x.png\" alt=\"\" usemap=\"#Map\" border=\"0\"/>\n" +
//                "                <map name=\"Map\" id=\"Map\">\n" +
//                "                    <area alt=\"\" title=\"\" href=\"http://espn.go.com/\" shape=\"poly\" coords=\"1,1,53,2,52,16,1,16\" />\n" +
//                "                    <area alt=\"\" title=\"\" href=\"http://www.espnf1.com/\" shape=\"poly\" coords=\"72,1,93,2,93,17,72,17\" />\n" +
//                "                    <area alt=\"\" title=\"\" href=\"http://www.espnscrum.com/\" shape=\"poly\" coords=\"109,1,180,1,181,16,109,16\" />\n" +
//                "                    <area alt=\"\" title=\"\" href=\"http://www.espnfc.com/\" shape=\"poly\" coords=\"198,1,280,1,281,15,198,15\" />\n" +
//                "                    <area alt=\"\" title=\"\" href=\"http://footytips.com.au\" shape=\"poly\" coords=\"296,1,370,1,372,17,298,17\" />\n" +
//                "                </map>\n" +
//                "            </div>\n" +
//                "        </div>\n" +
//                "    </div>\n" +
//                "    <div class=\"copyright\">\n" +
//                "        &copy; ESPN Sports Media Ltd.\n" +
//                "    </div>\n" +
//                "</footer>\n" +
//                "\n" +
//                "    </div>\n" +
//                "    <!-- Footer End -->\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "    \n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "        <!-- espn.video dependencies -->\n" +
//                "        <script src=\"http://a.espncdn.com/combiner/c?js=jquery-1.7.1.js,plugins/jquery.metadata.js,plugins/jquery.pubsub.r5.js,plugins/ba-debug-0.4.js,espn.l10n.r12.js,espn.core.duo.r55.js,espn.storage.r6.js,espn.p13n.r16.js,espn.geo.r2.js\"></script>\n" +
//                "        <script src=\"/navigation/cricinfo/ci/video/js/min/espni.video-0.0.4.min.js?v=1430724683\"></script>\n" +
//                "        <!-- Main nav and general dependencies -->\n" +
//                "        <script src=\"/navigation/cricinfo/ci/video/js/min/libs.min.js\"></script>\n" +
//                "        <script src=\"/navigation/cricinfo/ci/video/js/min/plugins.min.v2.js\"></script>\n" +
//                "        \n" +
//                "        <!--  Init gpt files if not home and series pages -->\n" +
//                "        <script src=\"/navigation/cricinfo/ci/assets/js/src/espncricinfo.gpt.1.0.0.min.js?v=1438674618\"></script>\n" +
//                "        <script>\n" +
//                "        var __GPTenabled = true;\n" +
//                "        </script>\n" +
//                "\n" +
//                "    \n" +
//                "    \n" +
//                "\n" +
//                "\n" +
//                "        <script src=\"/navigation/cricinfo/ci/assets/js/plugins/jquery.cookie-1.4.1.js\"></script>\n" +
//                "        <script src=\"/navigation/cricinfo/ci/assets/js/src/cilogin.min.js?v=1434454204\"></script>\n" +
//                "        <script src=\"/navigation/cricinfo/ci/assets/js/src/main_nav.min.js?v=1436184202\"></script>\n" +
//                "        <!--[if (gte IE 6)&(lte IE 8)]>\n" +
//                "        <script type=\"text/javascript\" src=\"/navigation/cricinfo/ci/assets/js/plugins/selectivizr.modified.min.js\"></script>\n" +
//                "        <![endif]-->\n" +
//                "\n" +
//                "    \n" +
//                "    <script src=\"/navigation/cricinfo/ci/redesign/js/min/story.js?v=1439797454\"></script>\n" +
//                "    <script src=\"/navigation/cricinfo/ci/assets/js/src/poll.js?v=1415114704\"></script>\n" +
//                "    <script src=\"/navigation/cricinfo/ci/assets/js/src/cicomments.1.0.1.min.js?v=1437567158\"></script>\n" +
//                "    \n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "    <script type=\"text/javascript\">\n" +
//                "        (function(d, s, id) {\n" +
//                "          var js, fjs = d.getElementsByTagName(s)[0];\n" +
//                "          if (d.getElementById(id)) return;\n" +
//                "          js = d.createElement(s); js.id = id;\n" +
//                "          js.src = \"//connect.facebook.net/en_US/sdk.js?&appId=260890547115&version=v2.1\";\n" +
//                "          fjs.parentNode.insertBefore(js, fjs);\n" +
//                "        }(document, 'script', 'facebook-jssdk'));\n" +
//                "        window.fbAsyncInit = function() {\n" +
//                "            FB.init({\n" +
//                "                appId      : '260890547115',\n" +
//                "                xfbml      : true,\n" +
//                "                status     : true, // check login status\n" +
//                "                cookie     : true, // enable cookies to allow the server to access the session\n" +
//                "                oauth      : true,\n" +
//                "                version    : 'v2.1'\n" +
//                "            });\n" +
//                "            if(typeof cilogin !== \"undefined\"){\n" +
//                "                cilogin.facebookapiinit();\n" +
//                "            }\n" +
//                "        };\n" +
//                "    </script>\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "    \n" +
//                "\n" +
//                "<script type=\"text/javascript\" language=\"JavaScript\">\n" +
//                "\n" +
//                "\tvar s_account=\"wdgespcricinfo\";\t//(Excludes Wireless)\n" +
//                "\n" +
//                "\t//alert(window.location.hostname);\n" +
//                "\t//alert(s_account);\n" +
//                "</script>\n" +
//                "\n" +
//                "\n" +
//                "<script language=\"JavaScript\" type=\"text/javascript\" src=\"http://www.espncricinfo.com/navigation/cricinfo/omniture/omniture_global.js?1438674618\"></script>\n" +
//                "\n" +
//                "\n" +
//                "<script type=\"text/javascript\" language=\"JavaScript\">\n" +
//                "<!--\n" +
//                "\n" +
//                "//Clearing variable values for link tracking call\n" +
//                "function clrLnkTrckVar(){\t\n" +
//                "\t//Clear variables\n" +
//                "\ts_omni.pageName = \"\";\n" +
//                "\ts_omni.server = \"\";\n" +
//                "\ts_omni.prop1 = \"\";\n" +
//                "\ts_omni.prop3 = \"\";\n" +
//                "\ts_omni.eVar2 = \"\";\n" +
//                "}\n" +
//                "\n" +
//                "//Link tracking like Video Tab, Video headlines, audio downloads etc.\n" +
//                "function lnkTrackVals(name){\n" +
//                "\n" +
//                "\t//alert(\"Video Tab\");\n" +
//                "\tclrLnkTrckVar(); //Clear variables\n" +
//                "\t\n" +
//                "\tvar lnkTrck = name;\n" +
//                "\ts_omni.prop3 = s_omni.eVar2 = lnkTrck;\n" +
//                "\ts_omni.t();\n" +
//                "\t//alert(lnkTrck);\n" +
//                "\t\n" +
//                "}\n" +
//                "\n" +
//                "/* You may give each page an identifying name, server, and channel on the next lines. */\n" +
//                "s_omni.server = window.location.host; // Server from the Host\n" +
//                "s_omni.prop1 = \"cricinfo\";\n" +
//                "\n" +
//                "//s_omni.pageName = omniPageName.toLowerCase(); //Omniture Page Name(S)\n" +
//                "\n" +
//                "if(typeof omniSiteSection2 != 'undefined' && typeof omniSiteSubSection3 != 'undefined'){\n" +
//                "\ts_omni.pageName = omniSiteSection1.toLowerCase() + \":\" + omniSiteSection2.toLowerCase() + \":\" + omniSiteSection3.toLowerCase() + \":\" + omniPageName.toLowerCase(); \t//Page name\n" +
//                "}else if(typeof omniSiteSection2 != 'undefined'){\n" +
//                "\ts_omni.pageName = omniSiteSection1.toLowerCase() + \":\" + omniSiteSection2.toLowerCase() + \":\" + omniPageName.toLowerCase(); \t\t\t\t\t\t\t\t\t\t\t\t//Page name\n" +
//                "}else{\n" +
//                "\ts_omni.pageName = omniSiteSection1.toLowerCase() + \":\" + omniPageName.toLowerCase(); \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t//Page name\n" +
//                "}\n" +
//                "\n" +
//                "var omniPath = location.pathname;\n" +
//                "//alert(omniPath);\n" +
//                "if(typeof omniSiteSection1 != 'undefined'){\n" +
//                "\ts_omni.channel = omniSiteSection1.toLowerCase();\n" +
//                "}\n" +
//                "\n" +
//                "//Error page variables\n" +
//                "if(typeof omniPageType == 'undefined'){\n" +
//                "\ts_omni.pageType = \"\";\n" +
//                "}\n" +
//                "else{\n" +
//                "\ts_omni.pageType = \"errorPage\";\n" +
//                "\ts_omni.pageName = \"\";\n" +
//                "\ts_omni.server = \"\";\n" +
//                "\ts_omni.prop1 = \"\";\t\n" +
//                "}\n" +
//                "\n" +
//                "if(typeof omniCt != 'undefined'){\n" +
//                "\ts_omni.prop4 = omniCt.toLowerCase();\t//Content type\n" +
//                "}\n" +
//                "\n" +
//                "if(typeof omniSiteSection2 != 'undefined' && typeof omniSiteSubSection3 != 'undefined'){\n" +
//                "\ts_omni.prop5 = s_omni.channel + \":\" +  omniSiteSection2.toLowerCase() + \":\"+  omniSiteSubSection3.toLowerCase();\n" +
//                "}else if(typeof omniSiteSection2 != 'undefined'){\n" +
//                "\ts_omni.prop5 = s_omni.channel + \":\" +  omniSiteSection2.toLowerCase();\n" +
//                "} else {\n" +
//                "\ts_omni.prop5 = omniSiteSection1.toLowerCase() + \":\" + omniPageName.toLowerCase();\n" +
//                "}\n" +
//                "\n" +
//                "if(typeof omniStoryId != 'undefined'){\n" +
//                "\ts_omni.prop15 = s_omni.eVar20 = omniStoryId; //Story Object id\t\t\n" +
//                "}\n" +
//                "s_omni.prop16 = s_omni.eVar12 = cqanswer //Country where the page is viewed\n" +
//                "s_omni.prop17 = s_omni.eVar9 = \"en\" // Language Code\n" +
//                "\n" +
//                "if(typeof omniAuthId != 'undefined'){\n" +
//                "\ts_omni.prop23 = s_omni.eVar10 = omniAuthId; //Columnist id\t\t\n" +
//                "}\n" +
//                "\n" +
//                "s_omni.prop25 = s_omni.eVar19 = \"cricket\"; // Sport\n" +
//                "\n" +
//                "//Prop29 for capturing the Registered users\n" +
//                "var user_name = (typeof getCookie !== \"undefined\") && getCookie('un');\n" +
//                "if (user_name != \"\");\n" +
//                "{\n" +
//                "\tif (user_name == null || undefined)\n" +
//                "\t{\n" +
//                "\t\ts_omni.prop29 = \"anonymous\";\n" +
//                "}\n" +
//                "\telse\n" +
//                "\t{\n" +
//                "\ts_omni.prop29 = \"Registered:Logged in Active\";\n" +
//                "\t}\n" +
//                "}\n" +
//                "\n" +
//                "//Prop32 for capturing Player and Grounds ID for the Most viewed objects\n" +
//                "if(typeof omniMVO != 'undefined'){\n" +
//                "\ts_omni.prop32 = omniMVO; //Most viewed objects for Plyers and Grounds\t\t\n" +
//                "}\n" +
//                "\n" +
//                "//Prop42 for capturing Hawkeye tabs\n" +
//                "if(typeof omniHawk != 'undefined'){\n" +
//                "\ts_omni.prop33 = omniHawk; //capturing Hawkeye tabs\t\n" +
//                "}\n" +
//                "\n" +
//                "s_omni.hier1 = s_omni.pageName;\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t//Content Hierarchy\n" +
//                "if(typeof omniSrhType != 'undefined'){\n" +
//                "\ts_omni.eVar11 = omniSrhType;\n" +
//                "}\n" +
//                "s_omni.eVar13 = s_omni.prop1 + \":\" + s_omni.pageName; \t\t\t\t\t\t//Page name\n" +
//                "\n" +
//                "//s_omni.prop42 = omniHawk;\n" +
//                "\n" +
//                "/************* DO NOT ALTER ANYTHING BELOW THIS LINE ! **************/\n" +
//                "var s_code=s_omni.t();if(s_code)document.write(s_code)//-->\n" +
//                "\n" +
//                "</script>\n" +
//                "<!-- End SiteCatalyst code version: H.14. -->\n" +
//                "\n" +
//                "<!-- Begin comScore Tag -->\n" +
//                "<script>\n" +
//                "var _comscore = _comscore || [];\n" +
//                "_comscore.push({ c1: \"2\", c2: \"3000005\" });\n" +
//                "(function() {\n" +
//                "var s = document.createElement(\"script\"), el =\n" +
//                "document.getElementsByTagName(\"script\")[0]; s.async = true;\n" +
//                "s.src = (document.location.protocol == \"https:\" ? \"https://sb\" : \"http://b\") + \".scorecardresearch.com/beacon.js\";\n" +
//                "el.parentNode.insertBefore(s, el);\n" +
//                "})();\n" +
//                "</script>\n" +
//                "<!-- End comScore Tag -->\n" +
//                "\n" +
//                "    \n" +
//                "<script type='text/javascript'>\n" +
//                "    var _sf_async_config={};\n" +
//                "    /** CONFIGURATION START **/\n" +
//                "    _sf_async_config.uid = 26455;\n" +
//                "    _sf_async_config.domain = 'espncricinfo.com';\n" +
//                "    _sf_async_config.useCanonical = true;\n" +
//                "    _sf_async_config.authors  = 'lk';\n" +
//                "    _sf_async_config.sections = 'Story';\n" +
//                "    /** CONFIGURATION END **/\n" +
//                "    (function(){\n" +
//                "      function loadChartbeat() {\n" +
//                "        window._sf_endpt=(new Date()).getTime();\n" +
//                "        var e = document.createElement('script');\n" +
//                "        e.setAttribute('language', 'javascript');\n" +
//                "        e.setAttribute('type', 'text/javascript');\n" +
//                "        e.setAttribute('src',\n" +
//                "           (('https:' == document.location.protocol) ? 'https://a248.e.akamai.net/chartbeat.download.akamai.com/102508/' : 'http://static.chartbeat.com/') +\n" +
//                "           'js/chartbeat.js');\n" +
//                "        document.body.appendChild(e);\n" +
//                "      }\n" +
//                "      var oldonload = window.onload;\n" +
//                "      window.onload = (typeof window.onload != 'function') ?\n" +
//                "      loadChartbeat : function() { oldonload(); loadChartbeat(); };\n" +
//                "    })();\n" +
//                "</script>\n" +
//                "\n" +
//                "        \n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "<script type=\"text/javascript\">\n" +
//                "\n" +
//                "function getOutbrainWidgetId(){\n" +
//                "    __espni_outbrain.widget_id_desktop = '';\n" +
//                "    __espni_outbrain.widget_id_mobile = '';\n" +
//                "    if(isMobile.phone){\n" +
//                "        __espni_outbrain.widget_id_mobile = __espni_outbrain.mobile[__espni_outbrain.cluster];\n" +
//                "    }else{\n" +
//                "        __espni_outbrain.widget_id_desktop = __espni_outbrain.desktop[__espni_outbrain.cluster];\n" +
//                "    }\n" +
//                "}\n" +
//                "// start outbrain for mobile\n" +
//                "if((typeof __espni_outbrain !== 'undefined')&&(typeof isMobile !== 'undefined')){\n" +
//                "    var outbrain_div = $('.outbrain-module .OUTBRAIN');\n" +
//                "    if((outbrain_div.length > 0) && (typeof __espni_outbrain.cluster !== 'undefined')){\n" +
//                "        getOutbrainWidgetId();\n" +
//                "        if(isMobile.phone){\n" +
//                "            outbrain_div.attr(\"data-widget-id\",__espni_outbrain.widget_id_mobile)\n" +
//                "        }else{\n" +
//                "            outbrain_div.attr(\"data-widget-id\",__espni_outbrain.widget_id_desktop)\n" +
//                "        }\n" +
//                "    }\n" +
//                "}\n" +
//                "\n" +
//                "// end outbrain for mobile\n" +
//                "\n" +
//                "//load outbrain script tag\n" +
//                "(function(){\n" +
//                "    var ciout = document.createElement(\"script\");\n" +
//                "    ciout.async = true;\n" +
//                "    ciout.type = \"text/javascript\";\n" +
//                "    ciout.src = \"http://widgets.outbrain.com/outbrain.js\";\n" +
//                "    var node =document.getElementsByTagName(\"script\")[0];\n" +
//                "    node.parentNode.insertBefore(ciout, node);\n" +
//                "}());\n" +
//                "</script>\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "</div>\n" +
//                "</body>\n" +
//                "</html>\n" +
//                "\n";

//        final URL url = new URL("http://www.cricket.com.au/news/india-ready-for-sri-lanka-test-series-kumar-sangakkara-retirement/2015-08-10");
//        final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(url));
//        final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();

//        HTMLDocument htmldoc = new HTMLDocument(htmlCode);
//        String text = ArticleExtractor.INSTANCE.getText(htmlCode);

//        System.out.println(text);
    }

}
