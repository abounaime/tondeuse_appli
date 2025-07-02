<h1 style="color:white; font-size:28px;">Tondeuse Automatique</h1>

<p style="color:white; font-size:18px;">
API REST simulant le déplacement de tondeuses sur une pelouse rectangulaire.
</p>

<h2 style="color:white; font-size:22px;">Fonctionnement</h2>
<ul style="color:white; font-size:18px;">
<li>Plusieurs tondeuses avec position, orientation, instructions.</li>
<li>Instructions : G (gauche), D (droite), A (avancer).</li>
<li>Tondeuses bougent séquentiellement.</li>
<li>Retourne position finale.</li>
</ul>

<h2 style="color:white; font-size:22px;">Exemple d'entrée</h2>

<pre style="color:white; font-size:16px; background-color:#222; padding:10px; border-radius:5px;">
{
  "field": { "max_x": 5, "max_y": 5 },
  "mowers": [
    { "id": "mower1", "start_position": { "x": 1, "y": 2 }, "orientation": "N", "instructions": ["G","A","G","A","G","A","G","A","A"] },
    { "id": "mower2", "start_position": { "x": 3, "y": 3 }, "orientation": "E", "instructions": ["A","A","D","A","A","D","A","D","D","A"] }
  ]
}
</pre>

<h2 style="color:white; font-size:22px;">Lancement</h2>
<pre style="color:white; font-size:18px; background-color:#222; padding:10px; border-radius:5px;">
mvn spring-boot:run
</pre>

<h2 style="color:white; font-size:22px;">Améliorations possibles</h2>
<ul style="color:white; font-size:18px;">
<li>Renommer classes/méthodes pour plus de clarté.</li>
<li>Ajouter JavaDoc et validations (@NotNull, @Valid).</li>
<li>gestion des erreurs.</li>
<li>Tests unitaires et d’intégration plus poussés.</li>
</ul>
