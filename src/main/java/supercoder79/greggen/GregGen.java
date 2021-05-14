package supercoder79.greggen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.code.ModData;
import gregapi.data.MD;
import supercoder79.greggen.world.GregGenBOPWorldType;
import supercoder79.greggen.world.GregGenWorldType;
import supercoder79.greggen.world.biome.GregGenBiomes;

@Mod(modid = GregGen.MOD_ID, name = GregGen.MOD_NAME, version = GregGen.VERSION, dependencies="required-after:gregapi_post")
public final class GregGen extends Abstract_Mod {
	public static final String MOD_ID = "greggen";
	public static final String MOD_NAME = "greggen";
	public static final String VERSION = "0.1.1";

	public static ModData MOD_DATA = new ModData(MOD_ID, MOD_NAME);

	@SidedProxy(modId = MOD_ID, clientSide = "supercoder79.greggen.ClientProxy", serverSide = "supercoder79.greggen.ServerProxy")
    public static gregapi.api.Abstract_Proxy PROXY;

	@Override public String getModID() {return MOD_ID;}
	@Override public String getModName() {return MOD_NAME;}
	@Override public String getModNameForLog() {return "GregGen";}
	@Override public Abstract_Proxy getProxy() {return PROXY;}

	@Mod.EventHandler public final void onPreLoad           (FMLPreInitializationEvent aEvent) {onModPreInit(aEvent);}
	@Mod.EventHandler public final void onLoad              (FMLInitializationEvent aEvent) {onModInit(aEvent);}
	@Mod.EventHandler public final void onPostLoad          (FMLPostInitializationEvent aEvent) {onModPostInit(aEvent);}
	@Mod.EventHandler public final void onServerStarting    (FMLServerStartingEvent aEvent) {onModServerStarting(aEvent);}
	@Mod.EventHandler public final void onServerStarted     (FMLServerStartedEvent aEvent) {onModServerStarted(aEvent);}
	@Mod.EventHandler public final void onServerStopping    (FMLServerStoppingEvent       aEvent) {onModServerStopping(aEvent);}
	@Mod.EventHandler public final void onServerStopped     (FMLServerStoppedEvent        aEvent) {onModServerStopped(aEvent);}

	private GregGenWorldType worldType;
	private GregGenBOPWorldType worldTypeBop;

	@Override
	public void onModPreInit2(FMLPreInitializationEvent aEvent) {
		GregGenBiomes.init();

		this.worldType = new GregGenWorldType();

		// Add bop world type
		if (MD.BoP.mLoaded) {
			this.worldTypeBop = new GregGenBOPWorldType();
		}
	}

	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {

	}
	
	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		// Insert your PostInit Code here and not above
	}
	
	@Override
	public void onModServerStarting2(FMLServerStartingEvent aEvent) {
		// Insert your ServerStarting Code here and not above
	}
	
	@Override
	public void onModServerStarted2(FMLServerStartedEvent aEvent) {
		// Insert your ServerStarted Code here and not above
	}
	
	@Override
	public void onModServerStopping2(FMLServerStoppingEvent aEvent) {
		// Insert your ServerStopping Code here and not above
	}
	
	@Override
	public void onModServerStopped2(FMLServerStoppedEvent aEvent) {
		// Insert your ServerStopped Code here and not above
	}
}
