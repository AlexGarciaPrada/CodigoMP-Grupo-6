package combate2000lasecuela;

public class Constants {
    public static final String minionsFile = "./files/txtfiles/Esbirros.txt";
    public static final String weaponsFile = "./files/txtfiles/Armas.txt";
    public static final String armorsFile = "./files/txtfiles/Armaduras.txt";
    public static final String tfighterFile = "./files/txtfiles/TFighter.txt";
    public static final String   strentghFile = "./files/txtfiles/Fortalezas";
    public static final String weaknessFile = "./files/txtfiles/Debilidades";
    public static final String serRoute="./files/serfiles/";

    //Contantes para los Strings del Messagemanager

    public static final String[] initMenuText = {"Bienvenido a Combate2000", "1. Iniciar Sesion", "2. Registro", "3. Salir"};
    public static final String[] userTypeMenuText = {"Introduce el tipo de usuario que deseas registrar", "1. Jugador", "2. Operador", "3. Salir"};
    public static final String[] nickUsedText = {"El Nick escogido esta en uso por otro usuario"};
    public static final String[] userNotFoundText = {"El nick introducido no está registrado en el sistema"};
    public static final String[] wrongPasswordText = {"Password Incorrecta"};
    public static final String[] notCoincidencePasswordText = {"Las password no coinciden", "Vuelve a intentarlo"};
    public static final String [] userCorrectlyErasedText={"El usuario ha sido correctamente borrado"};
    public static final String [] questionErasetext={"Confirma que quieres borrar a este usuario","NO es una operacion reversible","1. Confirmar","2. Salir"};

    public static final String [] notChallengeToValidate={"No hay ningun desafio esperando ser validado"};
    public static final String [] validateChallengeText={"Deseas validar el desafio que se ha mostrado en pantalla","1. Si","2. No","3. Salir"};
    public static final String[] playerMenuText={"1. Crear Personaje","2. Borrar Personaje","3. Administrar equipo personaje"
            ,"4. Desafiar a otro jugador","5. Consultar registro de oro","6. Ver Ranking","7. Ver Estado del Luchador","8. Cerrar Sesion","9. Borrar Usuario"};
    public static final String [] operatorMenuText={"1. Editar personaje","2. Editar Equipo, Esbirros, Modificadores","3. Validar Desafios",
            "4. Bloquear Usuario","5. Desbloquear Usuario","6. Cerrar Sesion","7. Borrar Usuario"};
    public static final String [] alreadyBlockText={"Este usuario ya esta bloqueado"};
    public static final String [] alreadyUnblockText={"Este usuario ya esta desbloqueado"};
    public static final String[] playerBlockedText={"No se puede iniciar sesion porque el usuario esta bloqueado"};
    public static final String [] fighterTypesText={"Selecciona el tipo de personaje que deseas crear","1. Vampiro","2. Licantropo","3. Cazador"};
    public static final String [] alreadyFighterText={"Ya tienes un personaje creado","Borra el personaje actual para crear un personaje nuevo"};
    public static final String [] notFighterText={"No tienes un personaje creado","Crea un personaje para poder realizar esta accion"};
    public static final String [] eraseConfirmationText={"Confirma que quieres borrar tu personaje","ESTA ACCION IRREVERSIBLE","1. No","2. Si"};
    public static final String[] challengeInstructionText={"Desafiar a otro jugador: ","Primero debes introducir el nick del jugador que vas a retar","Por ultimo debes introducir la cantidad de oro que va a apostar (debes tener esa cantidad de oro)"};
    public static final String [] notFighterChallenged={"El jugador desafiado no tiene un luchador creado"};
    public static final String theUserText = "El usuario ";
    public static final String gold = "Oro a apostar: ";
    public static final String [] noCombatsText = {"No hay combates anteriores"};
    public static final String [] editFighterMenu={"Elige lo que deseas cambiar del personaje:","1. Nombre ","2. Raza","3. Tipo","4. Salir"};
    public static final String [] thisPlayerNotFighter={"El jugador seleccionado no tiene luchadores creados"};
    public static final String [] ilegalWeaponOperation={"No se ha podido realizar la operacion"};
    public static final String userRegistered2 = " ha sido registrado correctamente";
    public static final String logInText ="Inicio de Sesion";
    public static final String []  selectWeaponText={"Elige lo que quieras editar","1. Arma 1 (Equipada de manera predefinida","2. Arma2","3. Salir"};
    public static final String[]   selectEquipmentText={"Elige lo que deseas editar","1. Arma","2. Armadura","3. Salir"};
    public static final String editFighterText="Elige el usuario que vas a editar (para volver atras introduce SALIR)";

    public static final String exitAdvice = "(En caso de querer volver al menu anterior introduce SALIR en cualquiera de los campos)";
    public static final String registerText ="Registro";
    public static final String nameText="Nombre: ";
    public static final String passwordText="Password: ";
    public static final String nickText="Nick: ";
    public static final String armorSeparator="Armaduras: ";
    public static final String  weaponSeparator="Armas: ";
    public static final String confirmPasswordText="Repetir password: ";
    public static final String showWelcome="Bienvenido ";
    public static final String rankingText="Ranking de jugadores: ";
    public static final String selectUserToBlockText="Introduce el nick de usuario que deseas bloquear: ";
    public static final String blockUserText="BLOQUEO DE USUARIOS";
    public static final String selectUserToUnblockText="Introduce el nick de usuario que deseas desbloquear: ";
    public static final String unblockUserText="DESBLOQUEO DE USUARIOS";
    public static final String userBlock="ha sido bloqueado correctamente";
    public static final String userUnblock="ha sido desbloqueado correctamente";
    public static final String [] isTie ={" El resultado es un empate"};
    public static final String[] winner = {"Has ganado el combate"};
    public static final String[] loser = {"Has perdido el combate"};
    public static final String createFighter = "Elige el tipo de personaje que deseas crear: ";

    public static final String adviceErasetext="ESTAS A PUNTO DE BORRAR AL USUARIO ";
    public static final String [] editEquipmentMenu={"Elige lo que deseas cambiar del personaje:","1. Armas","2. Armaduras","3. Esbirros"};
    public static final String [] editMenu={"Elige que deseas hacer:","1. Eliminar","2. Añadir"};
    public static final String [] elementText={"Escriba el Id del elemento a eliminar: "};
    public static final String [] newElementText={"Escriba el Id del elemento a añadir: : "};
    public static final String [] elementNotEquipped = {"El elemento introducido no esta equipado"};
    public static final String [] elementAlreadyEquipped = {"El elemento introducido ya esta equipado"};
    public static final String [] elementDeleted = {"El elemento introducido se ha eliminado correctamente"};
    public static final String [] elementAdded = {"El elemento introducido se ha añadido correctamente"};



}