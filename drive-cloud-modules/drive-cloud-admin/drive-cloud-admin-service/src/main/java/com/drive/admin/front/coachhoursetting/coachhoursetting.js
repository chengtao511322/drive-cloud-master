import request from '@/utils/request'

                
// 分页查询教练发课设置列表
export function listPageCoachHourSetting(query) {
  return request({
    url: '/admin/coachHourSetting/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询教练发课设置列表
export function listCoachHourSetting(query) {
  return request({
    url: '/admin/coachHourSetting/findList',
    method: 'post',
    params: query
  })
}

// 查询教练发课设置详细
export function getCoachHourSetting(id) {
  return request({
    url: '/admin/coachHourSetting/' + id,
    method: 'get'
  })
}

// 新增教练发课设置
export function addCoachHourSetting(data) {
  return request({
    url: '/admin/coachHourSetting',
    method: 'post',
    data: data
  })
}

// 修改教练发课设置
export function updateCoachHourSetting(data) {
  return request({
    url: '/admin/coachHourSetting',
    method: 'put',
    data: data
  })
}

// 删除教练发课设置
export function delCoachHourSetting(id) {
  return request({
    url: '/admin/coachHourSetting/' + id,
    method: 'delete'
  })
}


// 状态启用/停用教练发课设置
export function changeStatus(data) {
  return request({
    url: '/admin/coachHourSetting/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出教练发课设置
export function exportCoachHourSetting(query) {
  return request({
    url: '/admin/coachHourSetting/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}