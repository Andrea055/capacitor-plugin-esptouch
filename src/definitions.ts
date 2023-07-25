export interface EsptouchV2Request {
  ssid: string;
  bssid: string;
  password: string;
  custom_data: string;
}

export interface EsptouchV2Response {
  response: string;
  error: string;
}

export interface EsptouchCapacitorPluginPlugin {
  EsptouchV2(options: EsptouchV2Request): Promise<EsptouchV2Response>;
}
