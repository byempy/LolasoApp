package com.example.miguel.lolstats;

import com.example.miguel.lolstats.Riot.RiotApiHelper;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeagueItem;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) throws RiotApiException {
        final String APIKEY = "RGAPI-1cc2ac3d-727d-4ab0-bf22-8e868fff4e4a";

        ApiConfig config = new ApiConfig().setKey(APIKEY);
        RiotApi api = new RiotApi(config);
        String primero = "No encontrado o pete";
        int puntos = 0;
        List<LeagueItem> listaInvocadores = null;
        boolean contieneChallengers = false;

            listaInvocadores = api.getChallengerLeagueByQueue(Platform.EUW, LeagueQueue.RANKED_SOLO_5x5).getEntries();

            listaInvocadores.sort(new Comparator<LeagueItem>() {
                @Override
                public int compare(LeagueItem o1, LeagueItem o2) {
                    return o2.getLeaguePoints()-o1.getLeaguePoints();
                }
            });

        if(listaInvocadores != null){
            String NAME = listaInvocadores.get(2).getPlayerOrTeamName();
            Summoner invocador =  api.getSummonerByName(Platform.EUW, NAME);

            MatchList matchList = null;

            matchList = api.getMatchListByAccountId(Platform.EUW, invocador.getAccountId());


            List<MatchReference> listaMatch = matchList.getMatches();

            listaMatch.sort(new Comparator<MatchReference>() {
                @Override
                public int compare(MatchReference o1, MatchReference o2) {
                    return (int)o2.getTimestamp()-(int)o1.getTimestamp();
                }
            });

            long gameId = listaMatch.get(99).getGameId();

            Match match = api.getMatch(Platform.EUW, gameId, invocador.getAccountId());

            Participant inv = match.getParticipantByAccountId(invocador.getAccountId());

            Champion champion = api.getDataChampion(Platform.EUW, inv.getChampionId());
            SummonerSpell spell1 = api.getDataSummonerSpell(Platform.EUW, inv.getSpell1Id());
            SummonerSpell spell2 = api.getDataSummonerSpell(Platform.EUW, inv.getSpell2Id());
            Boolean ganado = inv.getStats().isWin();


            System.out.println("Invocador: " + NAME);
            System.out.println("Campeón: " + champion.getName());
            System.out.println("Role: " + matchList.getMatches().get(99).getRole() + ", Lane: " + matchList.getMatches().get(99).getLane());
            System.out.println("Sum Spell 1: " + spell1.getName());
            System.out.println("Sum Spell 2: " + spell2.getName());
            System.out.println("Item 1: " + inv.getStats().getItem6());
            System.out.println("Ganado: " + ganado);

            System.out.println("Total de partidas: " + listaMatch.size());
            System.out.println("Versio api json: " + RiotApiHelper.getCurrentVersion());

        }
    }
}
