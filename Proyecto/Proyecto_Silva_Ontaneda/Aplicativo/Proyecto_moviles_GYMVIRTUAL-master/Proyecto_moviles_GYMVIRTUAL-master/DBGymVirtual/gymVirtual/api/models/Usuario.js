/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_usuario:{
      type: 'string',
      autoIncrement:true,
      columnName:'idUsuario'
    },

    nombre_usu:{
      type: 'string',
      required:true,
      columnName:'nombreUsuario',
      maxLength:50
    },

    edad_usu:{
      type: 'string',
      defaultsTo:'15',
      columnName: 'edadUsuario'
    },

    sexo_usu:{
      type: 'string',
      defaultsTo: 'NA',
      isIn:['H','M'],
      columnName: 'sexoUsuario'
    },

    altura_usu:{
      type: 'string',
      required:true,
      columnName: 'alturaUsuario',
    },

    peso_usu:{
      type: 'string',
      required:true,
      columnName: 'pesoUsuario',
    },
    
    correo_usu:{
      type: 'string',
      required:true,
      isEmail: true,
      maxLength: 100,
      columnName: 'correoUsuario'
    },

    contrasenia_usu:{
      type: 'string',
      columnName: 'contraseniaUsuario'
    },

    rolUsuarios:{
      collection: 'rolUsuario',
      via: 'usuario'
    },

    recetasUsuario:{
      collection: 'recetaUsuario',
      via: 'usuario'
    },

    usuarioRutinas:{
      collection: 'usuarioRutina',
      via: 'usuario'
    }

  },

};

