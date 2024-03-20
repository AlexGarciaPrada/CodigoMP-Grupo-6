package combate2000lasecuela;

public class Constants {
    public static final String minionsFile = "./files/Esbirros.txt";
    public static final String weaponsFile = "./files/Armas.txt";
    public static final String armorsFile = "./files/Armaduras.txt";

    //Contantes para los Strings del Messagemanager

    public static final String[] initMenuText = {"Bienvenido a Combate2000", "1. Iniciar Sesion", "2. Registro", "3. Salir"};
    public static final String[] userTypeMenuText = {"Introduce el tipo de usuario que deseas registrar", "1. Jugador", "2. Operador", "3. Salir"};
    public static final String[] nickUsedText = {"El Nick escogido esta en uso por otro usuario"};
    public static final String[] userNotFoundText = {"El nick introducido no está registrado en el sistema"};
    public static final String[] wrongPasswordText = {"Password Incorrecta"};
    public static final String[] notCoincidencePasswordText = {"Las password no coinciden", "Vuelve a intentarlo"};
    public static final String [] userCorrectlyErasedText={"El usuario ha sido correctamente borrado"};
    public static final String [] questionErasetext={"Confirma que quieres borrar a este usuario","NO es una operacion revertible","1. Confirmar","2. Salir"};

    public static final String[] playerMenuText={"1. Crear Personaje","2. Borrar Personaje","3. Administrar equipo personaje"
            ,"4. Desafiar a otro jugador","5. Consultar registro de oro","6. Ver Ranking","7. Cerrar Sesion","8. Borrar Usuario"};
    public static final String [] operatorMenuText={"1. Editar personaje","2. Editar Equipo, Esbirros, Modificadores","3. Validar Desafios",
            "4. Bloquear Usuario","5. Desbloquear Usuario","6. Cerrar Sesion","7. Borrar Usuario"};


    public static final String userRegistered1 = "El usuario ";
    public static final String userRegistered2 = " ha sido registrado correctamente";
    public static final String logInText ="Inicio de Sesion";
    public static final String exitAdvice = "(En caso de querer volver al menu anterior introduce SALIR en cualquiera de los campos)";
    public static final String registerText ="Registro";
    public static final String nameText="Nombre: ";
    public static final String passwordText="Password: ";
    public static final String nickText="Nick: ";
    public static final String confirmPasswordText="Repetir password: ";
    public static final String showWelcome="Bienvenido ";

    public static final String adviceErasetext="ESTAS A PUNTO DE BORRAR AL USUARIO ";



}